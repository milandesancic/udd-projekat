package ftn.uns.ac.rs.uddprojekat.indexer;

import ftn.uns.ac.rs.uddprojekat.model.IndexUnit;
import ftn.uns.ac.rs.uddprojekat.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Indexer {

    @Autowired
    DocumentRepository documentRepository;

    public Indexer() {
    }

    public boolean add(IndexUnit unit){
        unit = documentRepository.index(unit);
        if(unit!=null){
            return true;
        }else{
            return false;
        }
    }
}
