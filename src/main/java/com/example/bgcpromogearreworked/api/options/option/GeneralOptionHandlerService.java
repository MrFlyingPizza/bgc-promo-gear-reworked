package com.example.bgcpromogearreworked.api.options.option;

import com.example.bgcpromogearreworked.persistence.entities.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralOptionHandlerService {

    private final OptionService optionService;

    Option handleOptionGet(Long optionId) {
        return optionService.
    }

}
