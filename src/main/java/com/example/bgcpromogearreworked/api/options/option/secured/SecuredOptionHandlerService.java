package com.example.bgcpromogearreworked.api.options.option.secured;

import com.example.bgcpromogearreworked.api.options.exceptions.OptionNotFoundException;
import com.example.bgcpromogearreworked.api.options.option.dto.secured.SecuredOptionCreate;
import com.example.bgcpromogearreworked.api.options.option.dto.secured.SecuredOptionMapper;
import com.example.bgcpromogearreworked.api.options.option.dto.secured.SecuredOptionPartialUpdate;
import com.example.bgcpromogearreworked.api.options.option.dto.secured.SecuredOptionUpdate;
import com.example.bgcpromogearreworked.persistence.entities.Option;
import com.example.bgcpromogearreworked.persistence.repositories.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class SecuredOptionHandlerService {

    private final OptionRepository optionRepo;
    private final SecuredOptionMapper mapper;

    public boolean checkOptionExists(Long id) {
        return optionRepo.existsById(id);
    }

    Option handleOptionCreate(@Valid SecuredOptionCreate optionCreate) {
        return optionRepo.save(mapper.fromCreate(optionCreate));
    }

    Option handleOptionGet(Long id) {
        return optionRepo.findById(id).orElseThrow(OptionNotFoundException::new);
    }

    Iterable<Option> handleOptionBatchGet() {
        return optionRepo.findAll();
    }

    Option handleOptionUpdate(@Valid SecuredOptionUpdate optionUpdate) {
        Option option = optionRepo.findById(optionUpdate.getId()).orElseThrow(OptionNotFoundException::new);
        option = mapper.fromUpdate(optionUpdate, option);
        return optionRepo.save(option);
    }

    Option handleOptionPartialUpdate(@Valid SecuredOptionPartialUpdate optionPartialUpdate) {
        Option option = optionRepo.findById(optionPartialUpdate.getId()).orElseThrow(OptionNotFoundException::new);
        option = mapper.fromPartialUpdate(optionPartialUpdate, option);
        return optionRepo.save(option);
    }

}
