package com.example.bgcpromogearreworked.api.options.optionvalue;

import com.example.bgcpromogearreworked.api.options.exceptions.OptionValueNotFoundException;
import com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured.*;
import com.example.bgcpromogearreworked.api.options.persistence.OptionValueId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/secured/options/{optionName}/values")
public class SecuredOptionValueController {

    private final OptionValueService service;
    private final SecuredOptionValueMapper mapper;

    @PostMapping
    private SecuredOptionValueResponse createOptionValue(@PathVariable String optionName,
                                                         @RequestBody SecuredOptionValueCreate optionValueCreate) {
        optionValueCreate.setId(optionName);
        return mapper.toResponse(service.handleOptionValueCreate(optionValueCreate));
    }

    @GetMapping("/{optionValue}")
    private SecuredOptionValueResponse getOptionValue(@PathVariable String optionName, @PathVariable String optionValue) {
        OptionValueId id = service.constructId(optionName, optionValue);
        if (!service.checkOptionValueExists(id)) {
            throw new OptionValueNotFoundException();
        }
        return mapper.toResponse(service.handleOptionValueGet(id));
    }

    @GetMapping
    private SecuredOptionValueBatchResponse getOptionValueBatch(@PathVariable String optionName) {
        return mapper.toBatchResponse(service.handleOptionValueBatchGet(optionName));
    }

    @PutMapping("/{optionValue}")
    private SecuredOptionValueResponse updateOptionValue(@PathVariable String optionName,
                                                         @PathVariable String optionValue,
                                                         @RequestBody SecuredOptionValueUpdate optionValueUpdate) {
        OptionValueId id = service.constructId(optionName, optionValue);
        if (!service.checkOptionValueExists(id)) {
            throw new OptionValueNotFoundException();
        }
        optionValueUpdate.setId(id);
        return mapper.toResponse(service.handleOptionValueUpdate(optionValueUpdate));
    }

    @PatchMapping("/{optionValue}")
    private SecuredOptionValueResponse updateOptionValuePartial(@PathVariable String optionName,
                                                                @PathVariable String optionValue,
                                                                @RequestBody SecuredOptionValuePartialUpdate optionValuePartialUpdate) {
        OptionValueId id = service.constructId(optionName, optionValue);
        if (!service.checkOptionValueExists(id)) {
            throw new OptionValueNotFoundException();
        }
        optionValuePartialUpdate.setId(id);
        return mapper.toResponse(service.handleOptionValuePartialUpdate(optionValuePartialUpdate));
    }

}
