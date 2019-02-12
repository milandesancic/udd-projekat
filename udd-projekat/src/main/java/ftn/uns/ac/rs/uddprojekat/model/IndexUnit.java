package ftn.uns.ac.rs.uddprojekat.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.List;

@Document(indexName = "naucnacentrala_dokumenti", type = "dokument" )
public class IndexUnit {


    @Id
    private String title;
    private String text;
    private String keywords;
    private String category;
    private String magazine;
    private String document_abstract;
    private String path;
    private List<Autor> autors;

    public IndexUnit() {
        this.autors = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public String getDocument_abstract() {
        return document_abstract;
    }

    public void setDocument_abstract(String document_abstract) {
        this.document_abstract = document_abstract;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Autor> getAutors() {
        return autors;
    }

    public void setAutors(List<Autor> autors) {
        this.autors = autors;
    }
}
