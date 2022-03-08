package com.example.bgcpromogearreworked.api.options.optionvalue;

import com.example.bgcpromogearreworked.api.options.exceptions.OptionValueNotFoundException;
import com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured.SecuredOptionValueCreate;
import com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured.SecuredOptionValueMapper;
import com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured.SecuredOptionValuePartialUpdate;
import com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured.SecuredOptionValueUpdate;
import com.example.bgcpromogearreworked.api.options.persistence.OptionValue;
import com.example.bgcpromogearreworked.api.options.persistence.OptionValueId;
import com.example.bgcpromogearreworked.api.options.persistence.OptionValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionValueService {

    private final OptionValueRepository optionValueRepo;
    private final SecuredOptionValueMapper mapper;

    OptionValueId constructId(String name, String value) {
        return new OptionValueId(name, value);
    }

    boolean checkOptionValueExists(OptionValueId optionValueId) {
        return optionValueRepo.existsById(optionValueId);
    }

    OptionValue handleOptionValueCreate(SecuredOptionValueCreate optionValueCreate) {
        return optionValueRepo.save(mapper.fromCreate(optionValueCreate));
    }

    OptionValue handleOptionValueUpdate(SecuredOptionValueUpdate optionValueUpdate) {
        OptionValue optionValue = optionValueRepo.findById(optionValueUpdate.getId()).orElseThrow(OptionValueNotFoundException::new);
        return optionValueRepo.save(mapper.fromUpdate(optionValueUpdate, optionValue));
    }

    OptionValue handleOptionValuePartialUpdate(SecuredOptionValuePartialUpdate optionValuePartialUpdate) {
        OptionValue optionValue = optionValueRepo.findById(optionValuePartialUpdate.getId()).orElseThrow(OptionValueNotFoundException::new);
        return optionValueRepo.save(mapper.fromPartialUpdate(optionValuePartialUpdate, optionValue));
    }

    OptionValue handleOptionValueGet(OptionValueId optionValueId) {
        return optionValueRepo.findById(optionValueId).orElseThrow(OptionValueNotFoundException::new);
    }

    Iterable<OptionValue> handleOptionValueBatchGet(String name) {
        return optionValueRepo.findAllByName(name);
    }

}
