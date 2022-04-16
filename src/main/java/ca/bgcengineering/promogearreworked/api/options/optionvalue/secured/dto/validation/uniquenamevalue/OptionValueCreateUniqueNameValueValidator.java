package ca.bgcengineering.promogearreworked.api.options.optionvalue.secured.dto.validation.uniquenamevalue;

import ca.bgcengineering.promogearreworked.api.options.optionvalue.secured.dto.SecuredOptionValueCreate;
import ca.bgcengineering.promogearreworked.persistence.repositories.OptionValueRepository;
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
