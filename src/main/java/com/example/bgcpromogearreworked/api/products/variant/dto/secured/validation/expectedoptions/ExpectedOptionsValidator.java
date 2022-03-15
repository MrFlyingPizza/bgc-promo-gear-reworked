package com.example.bgcpromogearreworked.api.products.variant.dto.secured.validation.expectedoptions;

import com.example.bgcpromogearreworked.persistence.entities.Option;
import com.example.bgcpromogearreworked.persistence.entities.ProductRepository;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;

import java.util.Iterator;
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
        Set<Option> expectedOptions = productRepo.findById(productId).orElseThrow().getOptions();
        Set<Option> givenOptions = optionValueIds.stream()
                .map(id -> optionValueRepo.findById(id).orElseThrow().getOption())
                .collect(Collectors.toSet());
        Iterator<Option> givenOptionsIterator = givenOptions.iterator();

        boolean valid = true;
        while (valid && givenOptionsIterator.hasNext()) {
            if (!expectedOptions.contains(givenOptionsIterator.next())) {
                valid = false;
            }
        }
        return valid;
    }

}
