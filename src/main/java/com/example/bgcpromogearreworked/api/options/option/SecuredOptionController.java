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

    @GetMapping("/{optionName}")
    private SecuredOptionResponse getOption(@PathVariable String optionName) {
        if (!service.checkOptionExists(optionName)) {
            throw new OptionNotFoundException();
        }
        return mapper.toResponse(service.handleOptionGet(optionName));
    }

    @GetMapping
    private SecuredOptionBatchResponse getOptionBatch() {
        return mapper.toBatchResponse(service.handleOptionBatchGet());
    }

    @PutMapping("/{optionName}")
    private SecuredOptionResponse updateOption(@PathVariable String optionName, @RequestBody SecuredOptionUpdate optionUpdate) {
        if (!service.checkOptionExists(optionName)) {
            throw new OptionNotFoundException();
        }
        optionUpdate.setId(optionName);
        return mapper.toResponse(service.handleOptionUpdate(optionUpdate));
    }

    @PatchMapping("/{optionName}")
    private SecuredOptionResponse updateOptionPartial(@PathVariable String optionName,
                                                      @RequestBody SecuredOptionPartialUpdate optionPartialUpdate) {
        if (!service.checkOptionExists(optionName)) {
            throw new OptionNotFoundException();
        }
        optionPartialUpdate.setId(optionName);
        return mapper.toResponse(service.handleOptionPartialUpdate(optionPartialUpdate));
    }

}
