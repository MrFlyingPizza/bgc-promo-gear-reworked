package com.example.bgcpromogearreworked.api.shared.exceptionhandling.dto;

import org.mapstruct.Mapper;

import javax.validation.ConstraintViolationException;

@Mapper(componentModel = "spring")
public interface GlobalExceptionResponseMapper {

    default ConstraintViolationResponse fromConstraintViolationException(ConstraintViolationException exception) {
        return new ConstraintViolationResponse(exception);
    }
}
