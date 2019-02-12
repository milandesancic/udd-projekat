package ftn.uns.ac.rs.uddprojekat.model.dto;

public class SearchResponeDto {

    private String title;
    private String text;
    private String keywords;
    private String category;
    private String magazine;
    private String hightlight;

    public SearchResponeDto() {
    }

    public SearchResponeDto(String title, String text, String keywords, String category, String magazine, String hightlight) {
        this.title = title;
        this.text = text;
        this.keywords = keywords;
        this.category = category;
        this.magazine = magazine;
        this.hightlight = hightlight;
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

    public String getHightlight() {
        return hightlight;
    }

    public void setHightlight(String hightlight) {
        this.hightlight = hightlight;
    }
}
