package com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured.validation.uniquenamevalue;

import com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured.SecuredOptionValueCreate;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionValueCreateUniqueNameValueValidator extends UniqueNameValueValidator
        implements ConstraintValidator<UniqueNameValue, SecuredOptionValueCreate> {

    @Autowired
    private OptionValueRepository repo;

    @Override
    public boolean isValid(SecuredOptionValueCreate optionValueCreate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(optionValueCreate.getOptionId(), optionValueCreate.getValue(), repo);
    }
}
