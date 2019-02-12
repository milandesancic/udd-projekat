package ftn.uns.ac.rs.uddprojekat.model.sql;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "reivewer")
public class ReviewerSql {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name ="category")
    private String category;

    @Column(name="email")
    private String email;

    @ElementCollection()
    private List<String> documentReviewed;

    public ReviewerSql() {
    }

    public ReviewerSql(Long id, String firstname, String lastname, String category, String email, List<String> documentReviewed) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.category = category;
        this.email = email;
        this.documentReviewed = documentReviewed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getDocumentReviewed() {
        return documentReviewed;
    }

    public void setDocumentReviewed(List<String> documentReviewed) {
        this.documentReviewed = documentReviewed;
    }
}
