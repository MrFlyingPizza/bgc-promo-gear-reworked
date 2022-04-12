package ca.bgcengineering.promogearreworked.api.options.option.secured;

import ca.bgcengineering.promogearreworked.api.options.option.OptionService;
import ca.bgcengineering.promogearreworked.api.options.option.secured.dto.SecuredOptionCreate;
import ca.bgcengineering.promogearreworked.api.options.option.secured.dto.SecuredOptionMapper;
import ca.bgcengineering.promogearreworked.api.options.option.secured.dto.SecuredOptionPartialUpdate;
import ca.bgcengineering.promogearreworked.api.options.option.secured.dto.SecuredOptionUpdate;
import ca.bgcengineering.promogearreworked.persistence.entities.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class SecuredOptionHandlerService {

    private final OptionService service;
    private final SecuredOptionMapper mapper;

    Option handleOptionCreate(@Valid SecuredOptionCreate optionCreate) {
        return service.createOption(optionCreate, mapper::fromCreate);
    }

    Option handleOptionGet(Long id) {
        return service.getOption(id);
    }

    List<Option> handleOptionBatchGet() {
        return service.getOptions();
    }

    Option handleOptionUpdate(@Valid SecuredOptionUpdate optionUpdate) {
        return service.updateOption(optionUpdate.getId(), optionUpdate, mapper::fromUpdate);
    }

    Option handleOptionPartialUpdate(@Valid SecuredOptionPartialUpdate optionPartialUpdate) {
        return service.updateOption(optionPartialUpdate.getId(), optionPartialUpdate, mapper::fromPartialUpdate);
    }

}
