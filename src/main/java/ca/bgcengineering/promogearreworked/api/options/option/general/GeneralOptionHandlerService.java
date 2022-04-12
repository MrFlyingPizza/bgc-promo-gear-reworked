package ca.bgcengineering.promogearreworked.api.options.option.general;

import ca.bgcengineering.promogearreworked.api.options.option.OptionService;
import ca.bgcengineering.promogearreworked.persistence.entities.Option;
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
