package ca.bgcengineering.promogearreworked.api.products.product.secured.dto.validation.publishablewithvariant;

import ca.bgcengineering.promogearreworked.api.products.product.secured.dto.SecuredProductUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SecuredProductUpdatePublishableWithVariantValidator extends PublishableWithVariantValidator
        implements ConstraintValidator<PublishableWithVariant, SecuredProductUpdate> {

    @Autowired
    private ProductRepository repo;

    @Override
    public boolean isValid(SecuredProductUpdate productUpdate, ConstraintValidatorContext context) {
        return validate(productUpdate.getId(), productUpdate.getIsPublished(), repo);
    }
}
