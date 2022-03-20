package com.example.bgcpromogearreworked.api.officelocations;

import com.example.bgcpromogearreworked.api.officelocations.exceptions.OfficeLocationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OfficeLocationExceptionHandler {

    @ExceptionHandler(OfficeLocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String handleOfficeLocationNotFound(OfficeLocationNotFoundException exception) {
        return "Office location could not be found.";
    }

}
