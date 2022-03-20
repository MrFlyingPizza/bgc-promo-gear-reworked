package com.example.bgcpromogearreworked.api.options.option.general;

import com.example.bgcpromogearreworked.api.options.option.OptionService;
import com.example.bgcpromogearreworked.persistence.entities.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralOptionHandlerService {

    private final OptionService optionService;

    Option handleOptionGet(Long optionId) {
        return optionService.getOption(optionId);
    }

    List<Option> handleOptionBatchGet() {
        return optionService.getOptions();
    }

}
