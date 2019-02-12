package ftn.uns.ac.rs.uddprojekat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.lucene.spatial3d.geom.GeoPoint;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

@Document(indexName = "naucnacentrala_recenzenti",type = "recenzent")
public class Reviewer {
    private String firstname;
    private String lastname;
    @JsonIgnore
    private GeoPoint location;
    private String categoory;
    private String email;

    public Reviewer() {
    }


    public Reviewer(String firstname, String lastname, GeoPoint location, String categoory, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.location = location;
        this.categoory = categoory;
        this.email = email;
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

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public String getCategoory() {
        return categoory;
    }

    public void setCategoory(String categoory) {
        this.categoory = categoory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
