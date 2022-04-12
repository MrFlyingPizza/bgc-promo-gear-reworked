package ca.bgcengineering.promogearreworked.api.shared.validation.constraints.optionvalueexists;

import ca.bgcengineering.promogearreworked.persistence.repositories.OptionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionValueExistsValidator implements ConstraintValidator<OptionValueExists, Long> {

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Override
    public boolean isValid(Long valueId, ConstraintValidatorContext constraintValidatorContext) {
        if (valueId == null) return true;
        return optionValueRepo.existsById(valueId);
    }
}
