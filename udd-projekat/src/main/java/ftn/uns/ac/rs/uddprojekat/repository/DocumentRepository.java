package ftn.uns.ac.rs.uddprojekat.repository;

import ftn.uns.ac.rs.uddprojekat.model.IndexUnit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DocumentRepository extends ElasticsearchRepository<IndexUnit,String> {
}
