package com.example.bgcpromogearreworked.api.options.optionvalue.secured.dto.validation.uniquenamevalue;

import com.example.bgcpromogearreworked.api.options.optionvalue.secured.dto.SecuredOptionValuePartialUpdate;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionValuePartialUpdateUniqueNameValueValidator extends UniqueNameValueValidator
        implements ConstraintValidator<UniqueNameValue, SecuredOptionValuePartialUpdate> {

    @Autowired
    private OptionValueRepository repo;

    @Override
    public boolean isValid(SecuredOptionValuePartialUpdate optionValuePartialUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(optionValuePartialUpdate.getOptionId(), optionValuePartialUpdate.getValue(), repo);
    }
}
