package com.example.bgcpromogearreworked.api.common.errorhandling;

import com.example.bgcpromogearreworked.api.common.errorhandling.dto.ConstraintViolationResponse;
import com.example.bgcpromogearreworked.api.common.errorhandling.dto.GlobalExceptionResponseMapper;
import lombok.RequiredArgsConstructor;
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

}
