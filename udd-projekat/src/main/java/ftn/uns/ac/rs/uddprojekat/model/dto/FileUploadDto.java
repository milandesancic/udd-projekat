package ftn.uns.ac.rs.uddprojekat.model.dto;

import ftn.uns.ac.rs.uddprojekat.model.Autor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FileUploadDto implements Serializable {

    private String title;
    private String keywords;
    private String apstract;
    private String category;
    private String magazine;
    private String jsonAutors;
    private List<Autor> autors;
    private MultipartFile file;



    public FileUploadDto() {
        this.autors =  new ArrayList<Autor>();
    }

    public FileUploadDto(String title, String keywords, String apstract, String category, String magazine, String jsonAutors, List<Autor> autors, MultipartFile file) {
        this.title = title;
        this.keywords = keywords;
        this.apstract = apstract;
        this.category = category;
        this.magazine = magazine;
        this.jsonAutors = jsonAutors;
        this.autors = autors;
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getApstract() {
        return apstract;
    }

    public void setApstract(String apstract) {
        this.apstract = apstract;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public String getJsonAutors() {
        return jsonAutors;
    }

    public void setJsonAutors(String jsonAutors) {
        this.jsonAutors = jsonAutors;
    }

    public List<Autor> getAutors() {
        return autors;
    }

    public void setAutors(List<Autor> autors) {
        this.autors = autors;
    }

    @Override
    public String toString() {
        return "FileUploadDto{" +
                "title='" + title + '\'' +
                ", keywords='" + keywords + '\'' +
                ", apstract='" + apstract + '\'' +
                ", category='" + category + '\'' +
                ", magazine='" + magazine + '\'' +
                ", autors='" + autors + '\'' +
                ", file=" + file +
                '}';
    }
}
