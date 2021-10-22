package ru.haazad.onlinestorage.api.exceptions;

import java.util.Date;

public class ApplicationError {
    private String serviceName;
    private String errorCode;
    private String userMessage;
    private Date date;

    public ApplicationError() {
    }

    public ApplicationError(String errorCode, String userMessage) {
        this.serviceName = "Online Storage";
        this.errorCode = errorCode;
        this.userMessage = userMessage;
        this.date = new Date();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
