package com.example.hotelmanagementsystem.model;
import java.io.Serializable;


public class ErrorModel implements Serializable {

    private String message;
    private String field;
    private String description;

    public ErrorModel(String message)
    {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
