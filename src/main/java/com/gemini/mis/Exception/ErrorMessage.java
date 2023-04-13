package com.gemini.mis.Exception;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ErrorMessage {

private final int statusCode;
private final String timestamp;
private  final String description;

private List<String> errorMessage;


    public ErrorMessage(int statusCode, String timestamp, String description, String errorMessage) {
        super();
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.description = description;
        this.errorMessage = Arrays.asList(errorMessage);
    }
    public ErrorMessage(int statusCode, String timestamp, String description, List<String> errorMessage) {
        super();
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.description = description;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
    }
}
