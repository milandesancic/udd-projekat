package ftn.uns.ac.rs.uddprojekat.search;

import ftn.uns.ac.rs.uddprojekat.model.SearchType;
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
}
