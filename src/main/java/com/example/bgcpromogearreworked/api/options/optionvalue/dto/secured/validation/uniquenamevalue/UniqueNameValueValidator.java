package com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured.validation.uniquenamevalue;

import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;

public abstract class UniqueNameValueValidator {

    protected boolean validate(Long optionId, String value, OptionValueRepository repo) {
        if (value == null) {
            return true;
        }
        return !repo.existsByOptionIdAndValue(optionId, value);
    }

}
