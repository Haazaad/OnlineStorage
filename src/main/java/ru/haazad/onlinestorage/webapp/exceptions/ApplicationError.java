package ru.haazad.onlinestorage.webapp.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@NoArgsConstructor
public class ApplicationError {
    private String serviceName;
    private HttpStatus errorCode;
    private String userMessage;
    private Date date;

    public ApplicationError(HttpStatus errorCode, String userMessage) {
        this.serviceName = "Online Storage";
        this.errorCode = errorCode;
        this.userMessage = userMessage;
        this.date = new Date();
    }
}
