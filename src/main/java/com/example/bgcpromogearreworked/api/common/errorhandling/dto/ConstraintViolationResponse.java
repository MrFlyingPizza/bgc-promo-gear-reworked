package com.example.bgcpromogearreworked.api.common.errorhandling.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ConstraintViolationResponse {

    @RequiredArgsConstructor
    @Getter
    private static class ViolationMessage {
        private final List<String> path;
        private final String message;
    }

    private final List<ViolationMessage> messages;

    private static List<String> getPropertyPath(ConstraintViolation<?> constraintViolation) {
        Iterator<Path.Node> iterator = constraintViolation.getPropertyPath().iterator();
        List<String> path = new ArrayList<>();
        iterator.next();
        while (iterator.hasNext()) {
            String name = iterator.next().getName();
            if (name != null) {
                path.add(name);
            }
        }
        return path;
    }

    ConstraintViolationResponse(ConstraintViolationException exception) {
        this.messages = exception.getConstraintViolations().stream()
                .map(constraintViolation -> new ViolationMessage(
                        getPropertyPath(constraintViolation),
                        constraintViolation.getMessage()))
                .collect(Collectors.toList());
    }
}
