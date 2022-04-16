package ca.bgcengineering.promogearreworked.api.options.optionvalue.secured.dto.validation.uniquenamevalue;

import ca.bgcengineering.promogearreworked.api.options.optionvalue.secured.dto.SecuredOptionValueUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.OptionValueRepository;
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
