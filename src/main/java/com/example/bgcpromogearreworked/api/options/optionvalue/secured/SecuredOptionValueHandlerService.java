package com.example.bgcpromogearreworked.api.options.optionvalue.secured;

import com.example.bgcpromogearreworked.api.options.exceptions.OptionValueNotFoundException;
import com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured.SecuredOptionValueCreate;
import com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured.SecuredOptionValueMapper;
import com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured.SecuredOptionValuePartialUpdate;
import com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured.SecuredOptionValueUpdate;
import com.example.bgcpromogearreworked.persistence.repositories.OptionRepository;
import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class SecuredOptionValueHandlerService {

    private final OptionValueRepository optionValueRepo;
    private final OptionRepository optionRepo;
    private final SecuredOptionValueMapper mapper;

    OptionValue handleOptionValueCreate(@Valid SecuredOptionValueCreate optionValueCreate) {
        return optionValueRepo.save(mapper.fromCreate(optionValueCreate));
    }

    OptionValue handleOptionValueUpdate(@Valid SecuredOptionValueUpdate optionValueUpdate) {
        OptionValue optionValue = optionValueRepo.findById(optionValueUpdate.getId()).orElseThrow(OptionValueNotFoundException::new);
        return optionValueRepo.save(mapper.fromUpdate(optionValueUpdate, optionValue));
    }

    OptionValue handleOptionValuePartialUpdate(@Valid SecuredOptionValuePartialUpdate optionValuePartialUpdate) {
        OptionValue optionValue = optionValueRepo.findById(optionValuePartialUpdate.getId()).orElseThrow(OptionValueNotFoundException::new);
        return optionValueRepo.save(mapper.fromPartialUpdate(optionValuePartialUpdate, optionValue));
    }

    OptionValue handleOptionValueGet(Long valueId) {
        return optionValueRepo.findById(valueId).orElseThrow(OptionValueNotFoundException::new);
    }

    Iterable<OptionValue> handleOptionValueBatchGet(Long optionId) {
        return optionValueRepo.findAllByOptionId(optionId);
    }

}
