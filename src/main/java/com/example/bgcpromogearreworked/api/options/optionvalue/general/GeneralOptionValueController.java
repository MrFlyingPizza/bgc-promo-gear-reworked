package com.example.bgcpromogearreworked.api.options.optionvalue.general;

import com.example.bgcpromogearreworked.api.options.exceptions.OptionNotFoundException;
import com.example.bgcpromogearreworked.api.options.exceptions.OptionValueNotFoundException;
import com.example.bgcpromogearreworked.api.options.option.OptionService;
import com.example.bgcpromogearreworked.api.options.optionvalue.OptionValueService;
import com.example.bgcpromogearreworked.api.options.optionvalue.general.dto.GeneralOptionValueBatchResponse;
import com.example.bgcpromogearreworked.api.options.optionvalue.general.dto.GeneralOptionValueMapper;
import com.example.bgcpromogearreworked.api.options.optionvalue.general.dto.GeneralOptionValueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/options/{optionId}/values", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralOptionValueController {

    private final OptionValueService valueService;
    private final OptionService optionService;
    private final GeneralOptionValueHandlerService handlerService;
    private final GeneralOptionValueMapper mapper;

    @GetMapping("/{valueId}")
    private GeneralOptionValueResponse getOptionValue(@PathVariable Long optionId, @PathVariable Long valueId) {
        if (!optionService.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        if (!valueService.checkOptionValueExistsOnOption(optionId, valueId)) {
            throw new OptionValueNotFoundException();
        }
        return mapper.toResponse(handlerService.handleOptionValueGet(valueId));
    }

    @GetMapping
    private GeneralOptionValueBatchResponse getOptionValueBatch(@PathVariable Long optionId) {
        if (!optionService.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        return mapper.toBatchResponse(handlerService.handleOptionValueBatchGet(optionId));
    }

}
