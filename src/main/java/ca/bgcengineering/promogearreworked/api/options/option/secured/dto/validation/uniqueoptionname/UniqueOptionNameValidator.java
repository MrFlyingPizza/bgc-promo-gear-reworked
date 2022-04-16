package ca.bgcengineering.promogearreworked.api.options.option.secured.dto.validation.uniqueoptionname;

import ca.bgcengineering.promogearreworked.persistence.repositories.OptionRepository;

public abstract class UniqueOptionNameValidator {

    protected static boolean validate(String name, OptionRepository repo) {
        if (name == null) {
            return true;
        }
        return !repo.existsByName(name);
    }

}
