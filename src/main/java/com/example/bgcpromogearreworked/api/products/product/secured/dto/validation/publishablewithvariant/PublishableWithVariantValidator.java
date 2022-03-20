package com.example.bgcpromogearreworked.api.products.product.secured.dto.validation.publishablewithvariant;

import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;

public abstract class PublishableWithVariantValidator {

    protected boolean validate(Long productId, Boolean isPublished, ProductRepository repo) {
        assert productId != null;
        if (isPublished == null || !isPublished) {
            return true;
        }
        return repo.getById(productId).getVariants().size() > 0;
    }
}
