package ca.bgcengineering.promogearreworked.api.options.option.secured;

import ca.bgcengineering.promogearreworked.api.options.exceptions.OptionNotFoundException;
import ca.bgcengineering.promogearreworked.api.options.option.OptionService;
import ca.bgcengineering.promogearreworked.api.options.option.secured.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/secured/options", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredOptionController {

    private final OptionService service;
    private final SecuredOptionHandlerService handlerService;
    private final SecuredOptionMapper mapper;

    @PostMapping
    private SecuredOptionResponse createOption(@RequestBody SecuredOptionCreate optionCreate) {
        return mapper.toResponse(handlerService.handleOptionCreate(optionCreate));
    }

    @GetMapping("/{optionId}")
    private SecuredOptionResponse getOption(@PathVariable Long optionId) {
        if (!service.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        return mapper.toResponse(handlerService.handleOptionGet(optionId));
    }

    @GetMapping
    private SecuredOptionBatchResponse getOptionBatch() {
        return mapper.toBatchResponse(handlerService.handleOptionBatchGet());
    }

    @PutMapping("/{optionId}")
    private SecuredOptionResponse updateOption(@PathVariable Long optionId, @RequestBody SecuredOptionUpdate optionUpdate) {
        if (!service.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        optionUpdate.setId(optionId);
        return mapper.toResponse(handlerService.handleOptionUpdate(optionUpdate));
    }

    @PatchMapping("/{optionId}")
    private SecuredOptionResponse updateOptionPartial(@PathVariable Long optionId,
                                                      @RequestBody SecuredOptionPartialUpdate optionPartialUpdate) {
        if (!service.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        optionPartialUpdate.setId(optionId);
        return mapper.toResponse(handlerService.handleOptionPartialUpdate(optionPartialUpdate));
    }

    // TODO: 2022-03-08 implement delete

}
