package ca.bgcengineering.promogearreworked.api.options.optionvalue.secured.dto.validation.uniquenamevalue;

import ca.bgcengineering.promogearreworked.persistence.repositories.OptionValueRepository;

public abstract class UniqueNameValueValidator {

    protected static boolean validate(Long optionId, String value, OptionValueRepository repo) {
        if (value == null) {
            return true;
        }
        return !repo.existsByOptionIdAndValue(optionId, value);
    }

}
