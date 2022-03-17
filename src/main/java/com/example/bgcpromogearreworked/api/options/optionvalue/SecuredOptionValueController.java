package com.example.bgcpromogearreworked.api.options.optionvalue;

import com.example.bgcpromogearreworked.api.options.exceptions.OptionNotFoundException;
import com.example.bgcpromogearreworked.api.options.exceptions.OptionValueNotFoundException;
import com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/secured/options/{optionId}/values")
public class SecuredOptionValueController {

    private final OptionValueService service;
    private final SecuredOptionValueMapper mapper;

    @PostMapping
    private SecuredOptionValueResponse createOptionValue(@PathVariable Long optionId,
                                                         @RequestBody SecuredOptionValueCreate optionValueCreate) {
        if (!service.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        optionValueCreate.setOptionId(optionId);
        return mapper.toResponse(service.handleOptionValueCreate(optionValueCreate));
    }

    @GetMapping("/{valueId}")
    private SecuredOptionValueResponse getOptionValue(@PathVariable Long optionId, @PathVariable Long valueId) {
        if (!service.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        if (!service.checkOptionValueExists(optionId, valueId)) {
            throw new OptionValueNotFoundException();
        }
        return mapper.toResponse(service.handleOptionValueGet(valueId));
    }

    @GetMapping
    private SecuredOptionValueBatchResponse getOptionValueBatch(@PathVariable Long optionId) {
        if (!service.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        return mapper.toBatchResponse(service.handleOptionValueBatchGet(optionId));
    }

    @PutMapping("/{valueId}")
    private SecuredOptionValueResponse updateOptionValue(@PathVariable Long optionId,
                                                         @PathVariable Long valueId,
                                                         @RequestBody SecuredOptionValueUpdate optionValueUpdate) {
        if (!service.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        if (!service.checkOptionValueExists(optionId, valueId)) {
            throw new OptionValueNotFoundException();
        }
        optionValueUpdate.setOptionId(optionId);
        optionValueUpdate.setId(valueId);
        return mapper.toResponse(service.handleOptionValueUpdate(optionValueUpdate));
    }

    @PatchMapping("/{valueId}")
    private SecuredOptionValueResponse updateOptionValuePartial(@PathVariable Long optionId,
                                                                @PathVariable Long valueId,
                                                                @RequestBody SecuredOptionValuePartialUpdate optionValuePartialUpdate) {
        if (!service.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        if (!service.checkOptionValueExists(optionId, valueId)) {
            throw new OptionValueNotFoundException();
        }
        optionValuePartialUpdate.setOptionId(optionId);
        optionValuePartialUpdate.setId(valueId);
        return mapper.toResponse(service.handleOptionValuePartialUpdate(optionValuePartialUpdate));
    }
    // TODO: 2022-03-08 implement delete
}
