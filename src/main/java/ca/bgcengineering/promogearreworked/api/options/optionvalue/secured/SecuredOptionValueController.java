package ca.bgcengineering.promogearreworked.api.options.optionvalue.secured;

import ca.bgcengineering.promogearreworked.api.options.exceptions.OptionNotFoundException;
import ca.bgcengineering.promogearreworked.api.options.exceptions.OptionValueNotFoundException;
import ca.bgcengineering.promogearreworked.api.options.option.OptionService;
import ca.bgcengineering.promogearreworked.api.options.optionvalue.OptionValueService;
import ca.bgcengineering.promogearreworked.api.options.optionvalue.secured.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/secured/options/{optionId}/values", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredOptionValueController {

    private final OptionService optionService;
    private final OptionValueService valueService;
    private final SecuredOptionValueHandlerService handlerService;
    private final SecuredOptionValueMapper mapper;

    @PostMapping
    private SecuredOptionValueResponse createOptionValue(@PathVariable Long optionId,
                                                         @RequestBody SecuredOptionValueCreate optionValueCreate) {
        if (!optionService.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        optionValueCreate.setOptionId(optionId);
        return mapper.toResponse(handlerService.handleOptionValueCreate(optionValueCreate));
    }

    @GetMapping("/{valueId}")
    private SecuredOptionValueResponse getOptionValue(@PathVariable Long optionId, @PathVariable Long valueId) {
        if (!optionService.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        if (!valueService.checkOptionValueExistsOnOption(optionId, valueId)) {
            throw new OptionValueNotFoundException();
        }
        return mapper.toResponse(handlerService.handleOptionValueGet(valueId));
    }

    @GetMapping
    private SecuredOptionValueBatchResponse getOptionValueBatch(@PathVariable Long optionId) {
        if (!optionService.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        return mapper.toBatchResponse(handlerService.handleOptionValueBatchGet(optionId));
    }

    @PutMapping("/{valueId}")
    private SecuredOptionValueResponse updateOptionValue(@PathVariable Long optionId,
                                                         @PathVariable Long valueId,
                                                         @RequestBody SecuredOptionValueUpdate optionValueUpdate) {
        if (!optionService.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        if (!valueService.checkOptionValueExistsOnOption(optionId, valueId)) {
            throw new OptionValueNotFoundException();
        }
        optionValueUpdate.setOptionId(optionId);
        optionValueUpdate.setId(valueId);
        return mapper.toResponse(handlerService.handleOptionValueUpdate(optionValueUpdate));
    }

    @PatchMapping("/{valueId}")
    private SecuredOptionValueResponse updateOptionValuePartial(@PathVariable Long optionId,
                                                                @PathVariable Long valueId,
                                                                @RequestBody SecuredOptionValuePartialUpdate optionValuePartialUpdate) {
        if (!optionService.checkOptionExists(optionId)) {
            throw new OptionNotFoundException();
        }
        if (!valueService.checkOptionValueExistsOnOption(optionId, valueId)) {
            throw new OptionValueNotFoundException();
        }
        optionValuePartialUpdate.setOptionId(optionId);
        optionValuePartialUpdate.setId(valueId);
        return mapper.toResponse(handlerService.handleOptionValuePartialUpdate(optionValuePartialUpdate));
    }
    // TODO: 2022-03-08 implement delete
}
