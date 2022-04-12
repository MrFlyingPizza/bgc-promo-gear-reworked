package ca.bgcengineering.promogearreworked.api.options.option.secured.dto.validation.uniqueoptionname;

import ca.bgcengineering.promogearreworked.persistence.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionCreateUniqueNameValidator extends UniqueOptionNameValidator
        implements ConstraintValidator<UniqueOptionName, String> {

    @Autowired
    private OptionRepository repo;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return validate(name, repo);
    }
}
