package ftn.uns.ac.rs.uddprojekat.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "test",type ="document",createIndex = true)
public class IndexUnit {


    @Id
    private String title;
    private String text;
    private String magazine_name;
    private String keywords;
    private String category;


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

    public String getMagazine_name() {
        return magazine_name;
    }

    public void setMagazine_name(String magazine_name) {
        this.magazine_name = magazine_name;
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

    @Override
    public String toString() {
        return "IndexUnit{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", magazine_name='" + magazine_name + '\'' +
                ", keywords='" + keywords + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
