package com.example.bgcpromogearreworked.api.options.optionvalue.secured;

import com.example.bgcpromogearreworked.api.options.optionvalue.OptionValueService;
import com.example.bgcpromogearreworked.api.options.optionvalue.secured.dto.SecuredOptionValueCreate;
import com.example.bgcpromogearreworked.api.options.optionvalue.secured.dto.SecuredOptionValueMapper;
import com.example.bgcpromogearreworked.api.options.optionvalue.secured.dto.SecuredOptionValuePartialUpdate;
import com.example.bgcpromogearreworked.api.options.optionvalue.secured.dto.SecuredOptionValueUpdate;
import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
class SecuredOptionValueHandlerService {

    private final OptionValueService valueService;
    private final SecuredOptionValueMapper mapper;

    OptionValue handleOptionValueCreate(@Valid SecuredOptionValueCreate optionValueCreate) {
        return valueService.createOptionValue(optionValueCreate, mapper::fromCreate);
    }

    OptionValue handleOptionValueUpdate(@Valid SecuredOptionValueUpdate optionValueUpdate) {
        return valueService.updateOptionValue(optionValueUpdate.getId(), optionValueUpdate, mapper::fromUpdate);
    }

    OptionValue handleOptionValuePartialUpdate(@Valid SecuredOptionValuePartialUpdate optionValuePartialUpdate) {
        return valueService.updateOptionValue(optionValuePartialUpdate.getId(), optionValuePartialUpdate, mapper::fromPartialUpdate);
    }

    OptionValue handleOptionValueGet(Long valueId) {
        return valueService.getOptionValue(valueId);
    }

    List<OptionValue> handleOptionValueBatchGet(Long optionId) {
        return valueService.getOptionValues(optionId);
    }

}
