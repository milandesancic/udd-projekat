package ftn.uns.ac.rs.uddprojekat.model.dto;

public class BoolQuery {

    private String fieldOne;
    private String fieldSecond;
    private String operation;
    private String valueOne;
    private String valueSecond;

    public BoolQuery() {
    }

    public BoolQuery(String fieldOne, String fieldSecond, String operation, String valueOne, String valueSecond) {
        this.fieldOne = fieldOne;
        this.fieldSecond = fieldSecond;
        this.operation = operation;
        this.valueOne = valueOne;
        this.valueSecond = valueSecond;
    }

    public String getFieldOne() {
        return fieldOne;
    }

    public void setFieldOne(String fieldOne) {
        this.fieldOne = fieldOne;
    }

    public String getFieldSecond() {
        return fieldSecond;
    }

    public void setFieldSecond(String fieldSecond) {
        this.fieldSecond = fieldSecond;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getValueOne() {
        return valueOne;
    }

    public void setValueOne(String valueOne) {
        this.valueOne = valueOne;
    }

    public String getValueSecond() {
        return valueSecond;
    }

    public void setValueSecond(String valueSecond) {
        this.valueSecond = valueSecond;
    }
}
