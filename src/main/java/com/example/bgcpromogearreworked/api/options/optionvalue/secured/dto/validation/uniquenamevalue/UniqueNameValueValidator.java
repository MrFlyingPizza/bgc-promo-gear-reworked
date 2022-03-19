package com.example.bgcpromogearreworked.api.options.optionvalue.secured.dto.validation.uniquenamevalue;

import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;

public abstract class UniqueNameValueValidator {

    protected static boolean validate(Long optionId, String value, OptionValueRepository repo) {
        if (value == null) {
            return true;
        }
        return !repo.existsByOptionIdAndValue(optionId, value);
    }

}
