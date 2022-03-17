package com.example.bgcpromogearreworked.api.options.option.dto.secured.validation.uniqueoptionname;

import com.example.bgcpromogearreworked.persistence.repositories.OptionRepository;

public abstract class UniqueOptionNameValidator {

    protected static boolean validate(String name, OptionRepository repo) {
        if (name == null) {
            return true;
        }
        return !repo.existsByName(name);
    }

}
