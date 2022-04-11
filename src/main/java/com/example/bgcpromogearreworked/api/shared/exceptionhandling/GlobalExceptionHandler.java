package com.example.bgcpromogearreworked.api.shared.exceptionhandling;

import com.example.bgcpromogearreworked.api.shared.exceptionhandling.dto.ConstraintViolationResponse;
import com.example.bgcpromogearreworked.api.shared.exceptionhandling.dto.GlobalExceptionResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final GlobalExceptionResponseMapper mapper;

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ConstraintViolationResponse handleConstraintViolation(ConstraintViolationException exception) {
        return mapper.fromConstraintViolationException(exception);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String handleBadPropertyReference(PropertyReferenceException exception) {
        return String.format("Request is invalid because '%s' is not a valid predicate property.", exception.getPropertyName());
    }

}
