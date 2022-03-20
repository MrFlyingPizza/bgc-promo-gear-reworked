package com.example.bgcpromogearreworked.api.options.option.secured;

import com.example.bgcpromogearreworked.api.options.option.OptionService;
import com.example.bgcpromogearreworked.api.options.option.secured.dto.SecuredOptionCreate;
import com.example.bgcpromogearreworked.api.options.option.secured.dto.SecuredOptionMapper;
import com.example.bgcpromogearreworked.api.options.option.secured.dto.SecuredOptionPartialUpdate;
import com.example.bgcpromogearreworked.api.options.option.secured.dto.SecuredOptionUpdate;
import com.example.bgcpromogearreworked.persistence.entities.Option;
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
