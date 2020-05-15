package com.etcenteprise.newsoftheearth.exceptions;

public class ErrorInfo {
    public final StringBuffer url;
    public String ex;
    private String message;

    ErrorInfo(StringBuffer url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }
    public void setMessage(String message) {
        this.message = message;
    }
}