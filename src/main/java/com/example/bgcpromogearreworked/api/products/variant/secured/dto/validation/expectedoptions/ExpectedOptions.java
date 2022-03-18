package com.example.bgcpromogearreworked.api.products.variant.secured.dto.validation.expectedoptions;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        VariantCreateExpectedOptionsValidator.class,
        VariantUpdateExpectedOptionsValidator.class,
        VariantPartialUpdateExpectedOptionsValidator.class
})
@Documented
public @interface ExpectedOptions {

    String message() default "{dto_validation.constraints.ExpectedOptions}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
