package ftn.uns.ac.rs.uddprojekat.search;


import ftn.uns.ac.rs.uddprojekat.model.IndexUnit;
import ftn.uns.ac.rs.uddprojekat.model.Reviewer;
import ftn.uns.ac.rs.uddprojekat.model.dto.SearchResponeDto;
import ftn.uns.ac.rs.uddprojekat.model.sql.ReviewerSql;
import ftn.uns.ac.rs.uddprojekat.repository.jpa.ReviewerRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ResultRetriever {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Autowired
    private ReviewerRepository reviewerRepository;

    public List<SearchResponeDto> getResults(QueryBuilder query) {

        SearchQuery sq = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withHighlightFields(new HighlightBuilder.Field("text"))
                .build();

        AggregatedPage<IndexUnit> result = elasticsearchTemplate.queryForPage(sq, IndexUnit.class, new SearchResultMapper() {

            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                List<SearchResponeDto> document = new ArrayList<SearchResponeDto>();
                for (SearchHit searchHit : searchResponse.getHits()) {
                    if (searchResponse.getHits().getHits().length <= 0) {
                        return null;
                    }
                    SearchResponeDto resultData = new SearchResponeDto();
                    resultData.setTitle((String) searchHit.getSource().get("title"));
                    resultData.setCategory((String) searchHit.getSource().get("category"));
                    resultData.setKeywords((String) searchHit.getSource().get("keywords"));
                    resultData.setMagazine((String) searchHit.getSource().get("magazine"));
                    resultData.setText((String) searchHit.getSource().get("text"));
                    resultData.setDocumentAbstract((String) searchHit.getSource().get("document_abstract"));
                    resultData.setPath((String) searchHit.getSource().get("path"));
                    if (searchHit.getHighlightFields() != null) {
                        StringBuilder highlights = new StringBuilder("...");

                        if (searchHit.getHighlightFields().get("text") != null) {
                            Text[] text = searchHit.getHighlightFields().get("text").fragments();
                            for (Text t : text) {
                                highlights.append(t.toString());
                                highlights.append("...");
                            }
                        }
                        resultData.setHightlight(highlights.toString());
                    }
                    document.add(resultData);
                }
                if (document.size() > 0) {
                    return new AggregatedPageImpl<T>((List<T>) document);
                }

                return null;
            }
        });

        List<SearchResponeDto> response = new ArrayList<>();
        if (result != null) {
            for (Object unit : result) {
                response.add((SearchResponeDto) unit);
            }
        }
        return response;
    }


    public Object getGeoResults(QueryBuilder query) {

        SearchQuery sq = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();


        return elasticsearchTemplate.queryForList(sq, Reviewer.class);

    }

    public Object getMltResults(QueryBuilder query) {
        SearchQuery sq = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();

        List<IndexUnit> resultList = elasticsearchTemplate.queryForList(sq, IndexUnit.class);
        Set<String> titleSet = new HashSet<>();
        for (IndexUnit doc : resultList) {
            titleSet.add(doc.getTitle());
        }
        Set<ReviewerSql> revSet = new HashSet<>();
        for(String title: titleSet){
            List<ReviewerSql> result =  reviewerRepository.findBydocumentReviewed(title);
            for (ReviewerSql rev :result){
                revSet.add(rev);
            }
        }


        return  revSet;
    }
}
