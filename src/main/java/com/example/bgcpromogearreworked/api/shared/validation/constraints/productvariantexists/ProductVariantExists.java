package com.example.bgcpromogearreworked.api.shared.validation.constraints.productvariantexists;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductVariantExistsValidator.class)
@Documented
public @interface ProductVariantExists {

    String message() default "{dto_validation.constraints.ProductVariantExists}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
