package com.example.bgcpromogearreworked.api.products.variant.dto.secured.validation.expectedoptioncount;

import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;

import java.util.List;

public abstract class ExpectedOptionCountValidator {

    protected static boolean validate(List<Long> optionValueIds, Long productId, ProductRepository productRepo) {
        return productRepo.findById(productId).orElseThrow().getOptions().size() == optionValueIds.size();
    }

}
