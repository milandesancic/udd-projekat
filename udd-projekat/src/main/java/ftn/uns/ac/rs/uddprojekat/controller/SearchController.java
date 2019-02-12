package ftn.uns.ac.rs.uddprojekat.controller;

import ftn.uns.ac.rs.uddprojekat.model.SearchType;
import ftn.uns.ac.rs.uddprojekat.model.dto.BoolQuery;
import ftn.uns.ac.rs.uddprojekat.model.dto.GeoLocation;
import ftn.uns.ac.rs.uddprojekat.model.dto.SimpleQuery;
import ftn.uns.ac.rs.uddprojekat.model.dto.TextDto;
import ftn.uns.ac.rs.uddprojekat.search.CustomQueryBuilder;
import ftn.uns.ac.rs.uddprojekat.search.ResultRetriever;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SearchController {

    @Autowired
    ResultRetriever resultRetriever;

    @PostMapping(value = "/search/standard")
    public ResponseEntity<?> searchTermQuery(@RequestBody SimpleQuery queryDto) {
        QueryBuilder query = CustomQueryBuilder.buildQuery(SearchType.REGULAR, queryDto.getField(), queryDto.getValue());
        return new ResponseEntity<>(resultRetriever.getResults(query), HttpStatus.OK);

    }

    @PostMapping(value = "/search/phrase")
    public ResponseEntity<?> searchPhraseQuery(@RequestBody SimpleQuery queryDto) {
        QueryBuilder query = CustomQueryBuilder.buildQuery(SearchType.PHRASE, queryDto.getField(), queryDto.getValue());
        return new ResponseEntity<>(resultRetriever.getResults(query), HttpStatus.OK);

    }

    @PostMapping(value = "/search/geo")
    public ResponseEntity<?> searchGeoQuery(@RequestBody GeoLocation geoDto) {
        QueryBuilder query = CustomQueryBuilder.buildGeoQuery(SearchType.GEO, geoDto.getLongitude(), geoDto.getLatitude());
        return new ResponseEntity<>(resultRetriever.getGeoResults(query), HttpStatus.OK);

    }

    @PostMapping(value = "/search/bool")
    public ResponseEntity<?> searchBool(@RequestBody BoolQuery queryDto) {
        QueryBuilder query = CustomQueryBuilder.buildBoolQuery(queryDto);
        return new ResponseEntity<>(resultRetriever.getResults(query), HttpStatus.OK);

    }

    @PostMapping(value = "/search/more_like_this")
    public ResponseEntity<?> searchMLTQuery(@RequestBody TextDto textDto) {
        System.out.println("Ovdije?");
        QueryBuilder query = CustomQueryBuilder.buildMTLQuery(textDto.getText());
        return new ResponseEntity<>(resultRetriever.getMltResults(query), HttpStatus.OK);
    }
}
