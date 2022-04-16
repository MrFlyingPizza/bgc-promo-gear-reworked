package ca.bgcengineering.promogearreworked.api.shared.validation.constraints.optionexists;

import ca.bgcengineering.promogearreworked.persistence.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionExistsValidator implements ConstraintValidator<OptionExists, Long> {

    @Autowired
    private OptionRepository repo;

    @Override
    public boolean isValid(Long optionId, ConstraintValidatorContext constraintValidatorContext) {
        if (optionId == null) return true;
        return repo.existsById(optionId);
    }
}
