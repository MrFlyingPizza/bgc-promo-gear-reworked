package com.example.bgcpromogearreworked.api.products.product.secured.dto.validation.publishablewithvariant;

import com.example.bgcpromogearreworked.api.products.product.secured.dto.SecuredProductUpdate;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SecuredProductUpdatePublishableWithVariantValidator extends PublishableWithVariantValidator
        implements ConstraintValidator<PublishableWithVariant, SecuredProductUpdate> {

    @Autowired
    private ProductRepository repo;

    @Override
    @Transactional
    public boolean isValid(SecuredProductUpdate productUpdate, ConstraintValidatorContext context) {
        return validate(productUpdate.getId(), productUpdate.getIsPublished(), repo);
    }
}
