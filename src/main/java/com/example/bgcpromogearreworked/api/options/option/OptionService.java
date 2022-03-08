package com.example.bgcpromogearreworked.api.options.option;

import com.example.bgcpromogearreworked.api.options.exceptions.OptionNotFoundException;
import com.example.bgcpromogearreworked.api.options.option.dto.secured.SecuredOptionCreate;
import com.example.bgcpromogearreworked.api.options.option.dto.secured.SecuredOptionMapper;
import com.example.bgcpromogearreworked.api.options.option.dto.secured.SecuredOptionPartialUpdate;
import com.example.bgcpromogearreworked.api.options.option.dto.secured.SecuredOptionUpdate;
import com.example.bgcpromogearreworked.api.options.persistence.Option;
import com.example.bgcpromogearreworked.api.options.persistence.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class OptionService {

    private final OptionRepository optionRepo;
    private final SecuredOptionMapper mapper;

    public boolean checkOptionExists(String optionId) {
        return optionRepo.existsById(optionId);
    }

    Option handleOptionCreate(@Valid SecuredOptionCreate optionCreate) {
        return optionRepo.save(mapper.fromCreate(optionCreate));
    }

    Option handleOptionGet(String optionId) {
        return optionRepo.findById(optionId).orElseThrow(OptionNotFoundException::new);
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
