package com.example.bgcpromogearreworked.api.shared.validation.constraints.optionvalueexists;

import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionValueExistsValidator implements ConstraintValidator<OptionValueExists, Long> {

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return optionValueRepo.existsById(id);
    }
}
