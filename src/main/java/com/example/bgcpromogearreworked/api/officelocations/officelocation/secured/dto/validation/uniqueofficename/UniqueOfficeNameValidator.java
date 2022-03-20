package com.example.bgcpromogearreworked.api.officelocations.officelocation.secured.dto.validation.uniqueofficename;

import com.example.bgcpromogearreworked.persistence.repositories.OfficeLocationRepository;

public abstract class UniqueOfficeNameValidator {

    protected boolean validate(String name, OfficeLocationRepository repo) {
        if (name == null) return true;
        return !repo.existsByName(name);
    }

}
