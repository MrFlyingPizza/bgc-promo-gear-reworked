package com.example.bgcpromogearreworked.api.options.option;

import com.example.bgcpromogearreworked.api.options.exceptions.OptionNotFoundException;
import com.example.bgcpromogearreworked.api.options.option.dto.secured.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/secured/options", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredOptionController {

    private final OptionService service;
    private final SecuredOptionMapper mapper;

    @PostMapping
    private SecuredOptionResponse createOption(@RequestBody SecuredOptionCreate optionCreate) {
        return mapper.toResponse(service.handleOptionCreate(optionCreate));
    }

    @GetMapping("/{optionId}")
    private SecuredOptionResponse getOption(@PathVariable Long optionId) {
        if (!service.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        return mapper.toResponse(service.handleOptionGet(optionId));
    }

    @GetMapping
    private SecuredOptionBatchResponse getOptionBatch() {
        return mapper.toBatchResponse(service.handleOptionBatchGet());
    }

    @PutMapping("/{optionId}")
    private SecuredOptionResponse updateOption(@PathVariable Long optionId, @RequestBody SecuredOptionUpdate optionUpdate) {
        if (!service.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        optionUpdate.setId(optionId);
        return mapper.toResponse(service.handleOptionUpdate(optionUpdate));
    }

    @PatchMapping("/{optionId}")
    private SecuredOptionResponse updateOptionPartial(@PathVariable Long optionId,
                                                      @RequestBody SecuredOptionPartialUpdate optionPartialUpdate) {
        if (!service.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        optionPartialUpdate.setId(optionId);
        return mapper.toResponse(service.handleOptionPartialUpdate(optionPartialUpdate));
    }

    // TODO: 2022-03-08 implement delete

}
