package com.example.bgcpromogearreworked.api.products.variant.dto.secured.validation.uniqueoptionset;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        VariantCreateUniqueOptionSetValidator.class,
        VariantUpdateUniqueOptionSetValidator.class,
        VariantPartialUpdateUniqueOptionSetValidator.class
})
@Documented
public @interface UniqueOptionSet {

    String message() default "{dto_validation.constraints.UniqueOptionSet}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
