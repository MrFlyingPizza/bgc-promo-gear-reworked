package com.example.bgcpromogearreworked.api.officelocations.officelocation.secured.dto.validation.uniqueofficename;

import com.example.bgcpromogearreworked.persistence.repositories.OfficeLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SecuredOfficeCreateUniqueOfficeNameValidator extends UniqueOfficeNameValidator implements ConstraintValidator<UniqueOfficeName, String> {

    @Autowired
    private OfficeLocationRepository repo;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return validate(name, repo);
    }
}
