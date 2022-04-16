package ca.bgcengineering.promogearreworked.api.officelocations.officelocation.secured.dto.validation.uniqueofficename;

import ca.bgcengineering.promogearreworked.persistence.repositories.OfficeLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueOfficeNameValidator implements ConstraintValidator<UniqueOfficeName, String> {

    @Autowired
    private OfficeLocationRepository locationRepo;

    protected boolean validate(String name, OfficeLocationRepository repo) {
        if (name == null) return true;
        return !repo.existsByName(name);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return validate(name, locationRepo);
    }
}
