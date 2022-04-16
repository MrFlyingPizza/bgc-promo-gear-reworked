package ca.bgcengineering.promogearreworked.api.options.option.general;

import ca.bgcengineering.promogearreworked.api.options.option.OptionService;
import ca.bgcengineering.promogearreworked.api.options.option.general.dto.GeneralOptionMapper;
import ca.bgcengineering.promogearreworked.api.options.option.general.dto.GeneralOptionResponse;
import ca.bgcengineering.promogearreworked.api.options.exceptions.OptionNotFoundException;
import ca.bgcengineering.promogearreworked.api.options.option.general.dto.GeneralOptionBatchResponse;
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
    private final GeneralOptionHandlerService handlerService;
    private final GeneralOptionMapper mapper;

    @GetMapping("/{optionId}")
    private GeneralOptionResponse getOption(@PathVariable Long optionId) {
        if (!service.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        return mapper.toResponse(handlerService.handleOptionGet(optionId));
    }

    @GetMapping
    private GeneralOptionBatchResponse getOptionBatch() {
        return mapper.toBatchResponse(handlerService.handleOptionBatchGet());
    }

}
