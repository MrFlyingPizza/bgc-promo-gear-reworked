package com.example.bgcpromogearreworked.api.products.product.dto.secured.validation;

import com.example.bgcpromogearreworked.api.products.persistence.*;
import com.example.bgcpromogearreworked.api.products.product.dto.secured.ProductUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductPublishableValidator implements ConstraintValidator<ProductPublishable, ProductUpdate> {

    @Autowired
    private ProductRepository productRepo;

    @Override
    public boolean isValid(ProductUpdate productUpdate, ConstraintValidatorContext constraintValidatorContext) {
        Boolean publish = productUpdate.getIsPublished();
        if (publish == null || !publish) {
            return true;
        }
        Long id = productUpdate.getId();
        Product product = productRepo.findById(id).orElseThrow();
        Set<ProductVariant> variants = product.getVariants();
        Set<String> optionNames = product.getOptions().stream().map(Option::getName).collect(Collectors.toSet());
        for (ProductVariant variant : variants) {
            Set<String> variantOptionNames = variant.getOptionValues().stream().map(OptionValue::getName).collect(Collectors.toSet());
            if (!variantOptionNames.equals(optionNames)) {
                return false;
            }
        }
        return true;
    }
}
