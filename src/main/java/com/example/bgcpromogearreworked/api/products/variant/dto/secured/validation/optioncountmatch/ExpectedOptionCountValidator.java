package com.example.bgcpromogearreworked.api.products.variant.dto.secured.validation.optioncountmatch;

import com.example.bgcpromogearreworked.persistence.entities.ProductRepository;

import java.util.List;

public abstract class ExpectedOptionCountValidator {

    protected boolean validate(List<Long> optionValueIds, Long productId, ProductRepository productRepo) {
        return productRepo.findById(productId).orElseThrow().getOptions().size() == optionValueIds.size();
    }

}
