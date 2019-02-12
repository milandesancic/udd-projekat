package ftn.uns.ac.rs.uddprojekat.model.dto;

public class SimpleQuery {

    private String value;
    private String field;

    public SimpleQuery() {
    }

    public SimpleQuery(String value, String field) {
        this.value = value;
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
