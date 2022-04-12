package ca.bgcengineering.promogearreworked.api.shared.validation.constraints.officelocationexists;

import ca.bgcengineering.promogearreworked.persistence.repositories.OfficeLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OfficeLocationExistsValidator implements ConstraintValidator<OfficeLocationExists, Long> {

    @Autowired
    private OfficeLocationRepository locationRepo;

    @Override
    public boolean isValid(Long locationId, ConstraintValidatorContext constraintValidatorContext) {
        if (locationId == null) return true;
        return locationRepo.existsById(locationId);
    }
}
