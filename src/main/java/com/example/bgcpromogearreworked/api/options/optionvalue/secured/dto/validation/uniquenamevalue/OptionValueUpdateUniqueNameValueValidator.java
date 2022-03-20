package com.example.bgcpromogearreworked.api.options.optionvalue.secured.dto.validation.uniquenamevalue;

import com.example.bgcpromogearreworked.api.options.optionvalue.secured.dto.SecuredOptionValueUpdate;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionValueUpdateUniqueNameValueValidator extends UniqueNameValueValidator
        implements ConstraintValidator<UniqueNameValue, SecuredOptionValueUpdate> {

    @Autowired
    private OptionValueRepository repo;

    @Override
    public boolean isValid(SecuredOptionValueUpdate optionValueUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(optionValueUpdate.getOptionId(), optionValueUpdate.getValue(), repo);
    }
}
