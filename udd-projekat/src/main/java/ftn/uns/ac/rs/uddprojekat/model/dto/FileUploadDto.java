package ftn.uns.ac.rs.uddprojekat.model.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class FileUploadDto implements Serializable {

    private String title;
    private String keywords;
    private String apstract;
    private String category;
    private MultipartFile file;

    public FileUploadDto() {
    }

    public FileUploadDto(String title, String keywords, String apstract, String category, MultipartFile file) {
        this.title = title;
        this.keywords = keywords;
        this.apstract = apstract;
        this.category = category;
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

    @Override
    public String toString() {
        return "FileUploadDto{" +
                "title='" + title + '\'' +
                ", keywords='" + keywords + '\'' +
                ", apstract='" + apstract + '\'' +
                ", category='" + category + '\'' +
                ", file=" + file +
                '}';
    }
}
