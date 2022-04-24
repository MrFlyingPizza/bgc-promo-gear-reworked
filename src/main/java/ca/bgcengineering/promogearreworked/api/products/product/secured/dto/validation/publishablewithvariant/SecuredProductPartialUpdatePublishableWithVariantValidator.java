package ca.bgcengineering.promogearreworked.api.products.product.secured.dto.validation.publishablewithvariant;

import ca.bgcengineering.promogearreworked.api.products.product.secured.dto.SecuredProductPartialUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SecuredProductPartialUpdatePublishableWithVariantValidator extends PublishableWithVariantValidator
        implements ConstraintValidator<PublishableWithVariant, SecuredProductPartialUpdate> {

    @Autowired
    private ProductRepository repo;

    @Override
    public boolean isValid(SecuredProductPartialUpdate productPartialUpdate, ConstraintValidatorContext context) {
        return validate(productPartialUpdate.getId(), productPartialUpdate.getIsPublished(), repo);
    }
}
