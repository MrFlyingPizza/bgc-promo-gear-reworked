package com.example.bgcpromogearreworked.api.options.optionvalue.general;

import com.example.bgcpromogearreworked.api.options.optionvalue.OptionValueService;
import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class GeneralOptionValueHandlerService {

    private final OptionValueService valueService;

    OptionValue handleOptionValueGet(Long valueId) {
        return valueService.getOptionValue(valueId);
    }

    List<OptionValue> handleOptionValueBatchGet(Long optionId) {
        return valueService.getOptionValues(optionId);
    }

}
