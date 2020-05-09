package com.etcenteprise.newsoftheearth.entities;

public class MailRequestAndResponse {
    private String name;
    private String to;
    private String from;
    private String subject;
    private String responseMessage;
    private Boolean responseStatus;

    public MailRequestAndResponse() {
    }

    public MailRequestAndResponse(String responseMessage, Boolean responseStatus) {
        this.responseMessage = responseMessage;
        this.responseStatus = responseStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Boolean getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Boolean responseStatus) {
        this.responseStatus = responseStatus;
    }
}
