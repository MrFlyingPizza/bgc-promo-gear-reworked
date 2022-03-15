package com.example.bgcpromogearreworked.api.products.variant.dto.secured.validation.uniqueoptionset;

import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import com.example.bgcpromogearreworked.persistence.entities.Product;
import com.example.bgcpromogearreworked.persistence.entities.ProductRepository;
import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class UniqueOptionSetValidator {

    // assumes that there are the same number of option values as option that were specified for the product.
    protected boolean validate(Long productId,
                               List<Long> optionValueIds,
                               ProductRepository productRepo,
                               OptionValueRepository optionValueRepo) {
        Product product = productRepo.getById(productId);
        Set<OptionValue> newOptionValues = optionValueIds.stream().map(id -> optionValueRepo.findById(id).orElseThrow())
                .collect(Collectors.toSet());
        for (ProductVariant variant : product.getVariants()) {
            if (variant.getOptionValues().equals(newOptionValues)) {
                return false;
            }
        }
        return true;
    }

}
