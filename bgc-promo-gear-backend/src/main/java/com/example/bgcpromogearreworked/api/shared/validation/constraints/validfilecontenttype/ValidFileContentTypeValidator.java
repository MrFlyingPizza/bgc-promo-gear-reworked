package com.example.bgcpromogearreworked.api.shared.validation.constraints.validfilecontenttype;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ValidFileContentTypeValidator implements ConstraintValidator<ValidFileContentType, MultipartFile> {

    private String[] acceptedMediaTypes;

    @Override
    public void initialize(ValidFileContentType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.acceptedMediaTypes = constraintAnnotation.acceptedMediaTypes();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file == null) {
            return true;
        }
        Iterator<String> iter = Arrays.stream(this.acceptedMediaTypes).iterator();
        boolean valid = true;
        while (iter.hasNext() && valid) {
            if (!Objects.equals(file.getContentType(), iter.next())) {
                valid = false;
            }
        }
        return valid;
    }
}
