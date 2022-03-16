package com.example.bgcpromogearreworked.api.products.variant.dto.secured.validation.expectedoptions;

import com.example.bgcpromogearreworked.persistence.entities.Option;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ExpectedOptionsValidator {

    // assumes that the list of optionValueIds contain IDs of existing OptionValues
    // assumes that the list of optionValueIds only has unique elements
    // assumes that product exists.
    protected static boolean validate(Long productId,
                               List<Long> optionValueIds,
                               ProductRepository productRepo,
                               OptionValueRepository optionValueRepo) {
        HashMap<Option, Boolean> optionsExpected = new HashMap<>();
        Set<Option> expectedOptions = productRepo.findById(productId).orElseThrow().getOptions();
        expectedOptions.forEach(option -> optionsExpected.put(option, false));
        Set<Option> givenOptions = optionValueIds.stream()
                .map(id -> optionValueRepo.findById(id).orElseThrow().getOption())
                .collect(Collectors.toSet());
        givenOptions.forEach(option -> optionsExpected.computeIfPresent(option, (currentOption, present) -> true));
        return !optionsExpected.containsValue(false);
    }

}
