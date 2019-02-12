package ftn.uns.ac.rs.uddprojekat.repository.jpa;

import ftn.uns.ac.rs.uddprojekat.model.sql.ReviewerSql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ReviewerRepository extends JpaRepository<ReviewerSql,Long> {

    public List<ReviewerSql> findBydocumentReviewed(String title);

}
