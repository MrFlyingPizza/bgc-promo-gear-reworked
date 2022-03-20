package com.example.bgcpromogearreworked.api.products.product.secured.dto.validation.publishablewithvariant;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        SecuredProductPartialUpdatePublishableWithVariantValidator.class,
        SecuredProductUpdatePublishableWithVariantValidator.class
})
@Documented
public @interface PublishableWithVariant {

    String message() default "{dto_validation.constraints.PublishableWithVariant}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
