package com.example.bgcpromogearreworked.api.shared.validation.exists.validators;

import com.example.bgcpromogearreworked.persistence.repositories.OptionRepository;
import com.example.bgcpromogearreworked.api.shared.validation.exists.annotations.OptionExists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionExistsValidator implements ConstraintValidator<OptionExists, Long> {

    @Autowired
    private OptionRepository repo;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return repo.existsById(id);
    }
}
