package ftn.uns.ac.rs.uddprojekat.search;

import ftn.uns.ac.rs.uddprojekat.model.SearchType;
import ftn.uns.ac.rs.uddprojekat.model.dto.BoolQuery;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MoreLikeThisQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import static org.elasticsearch.index.query.QueryBuilders.*;

public class CustomQueryBuilder {

    public static QueryBuilder buildQuery(SearchType type, String field, String value) {
        String errorMessage = "";
        if (field == null || field.equals("")) {
            errorMessage += "Field not specified";
        }
        if (value == null) {
            if (!errorMessage.equals("")) errorMessage += "\n";
            errorMessage += "Value not specified";
        }
        if (!errorMessage.equals("")) {
            throw new IllegalArgumentException(errorMessage);
        }
        QueryBuilder retVal = null;
        if(SearchType.REGULAR.equals(type)) {
            //Regular Search
            retVal = QueryBuilders.matchQuery(field, value);
        }else if(SearchType.PHRASE.equals(type)){
            //Fraze
            retVal = QueryBuilders.matchPhraseQuery(field,value);
        }
        if("autor".equals(field)) {
            //Ako pretrazujem po autorima uvijek ptratrazujem po imenu prezimenu i email adresi
         retVal = nestedQuery("autors", boolQuery()
                    .should(matchQuery("autors.firstname", value))
                    .should(matchQuery("autors.lastname", value)), ScoreMode.Avg);
        }
        return  retVal;
    }


    public static QueryBuilder buildGeoQuery(SearchType type, double longiture, double latitude) {
        QueryBuilder retVal = null;
        retVal = QueryBuilders.boolQuery().mustNot(QueryBuilders.geoDistanceQuery("location").geoDistance(GeoDistance.ARC).point(latitude,longiture).distance("100km"));
        return  retVal;
    }

    public static QueryBuilder buildMTLQuery(String text) {
        String[] fild = {"text"};
        String[] like = {text};
        MoreLikeThisQueryBuilder.Item[] items = {};
        QueryBuilder retVal = QueryBuilders.moreLikeThisQuery(fild,like,items).minTermFreq(1).minDocFreq(1);
        return  retVal;
    }

    public static QueryBuilder buildBoolQuery(BoolQuery queryDto) {

        QueryBuilder query1 = buildQuery(SearchType.REGULAR,queryDto.getFieldOne(),queryDto.getValueOne());
        QueryBuilder query2 = buildQuery(SearchType.REGULAR,queryDto.getFieldSecond(),queryDto.getValueSecond());
        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        if(queryDto.getOperation().equalsIgnoreCase("AND")){
            builder.must(query1);
            builder.must(query2);
        }else if(queryDto.getOperation().equalsIgnoreCase("OR")){
            builder.should(query1);
            builder.should(query2);
        }else{
            builder.must(query1);
            builder.mustNot(query2);
        }
        return builder;

    }
}
