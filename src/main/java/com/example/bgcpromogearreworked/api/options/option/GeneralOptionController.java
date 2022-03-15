package com.example.bgcpromogearreworked.api.options.option;

import com.example.bgcpromogearreworked.api.options.exceptions.OptionNotFoundException;
import com.example.bgcpromogearreworked.api.options.option.dto.general.GeneralOptionBatchResponse;
import com.example.bgcpromogearreworked.api.options.option.dto.general.GeneralOptionMapper;
import com.example.bgcpromogearreworked.api.options.option.dto.general.GeneralOptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/options", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralOptionController {

    private final OptionService service;
    private final GeneralOptionMapper mapper;

    @GetMapping("/{optionId}")
    private GeneralOptionResponse getOption(@PathVariable Long optionId) {
        if (!service.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        return mapper.toResponse(service.handleOptionGet(optionId));
    }

    @GetMapping
    private GeneralOptionBatchResponse getOptionBatch() {
        return mapper.toBatchResponse(service.handleOptionBatchGet());
    }

}
