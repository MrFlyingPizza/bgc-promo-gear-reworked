package ca.bgcengineering.promogearreworked.api.options.optionvalue.general;

import ca.bgcengineering.promogearreworked.api.options.optionvalue.OptionValueService;
import ca.bgcengineering.promogearreworked.persistence.entities.OptionValue;
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
