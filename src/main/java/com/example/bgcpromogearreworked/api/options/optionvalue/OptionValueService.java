package com.example.bgcpromogearreworked.api.options.optionvalue;

import com.example.bgcpromogearreworked.api.options.exceptions.OptionValueNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionValueService {

    private final OptionValueRepository valueRepo;

    public boolean checkOptionValueExists(Long optionId, Long valueId) {
        return valueRepo.existsByOptionIdAndId(optionId, valueId);
    }

    public OptionValue getOptionValue(Long valueId) {
        return valueRepo.findById(valueId).orElseThrow(OptionValueNotFoundException::new);
    }

}
