package com.example.bgcpromogearreworked.api.products.product.dto.secured.validation;

import com.example.bgcpromogearreworked.api.options.persistence.Option;
import com.example.bgcpromogearreworked.api.options.persistence.OptionValue;
import com.example.bgcpromogearreworked.api.products.persistence.*;
import com.example.bgcpromogearreworked.api.products.product.dto.secured.SecuredProductUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductPublishableValidator implements ConstraintValidator<ProductPublishable, SecuredProductUpdate> {

    @Autowired
    private ProductRepository productRepo;

    @Override
    public boolean isValid(SecuredProductUpdate productUpdate, ConstraintValidatorContext constraintValidatorContext) {
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
