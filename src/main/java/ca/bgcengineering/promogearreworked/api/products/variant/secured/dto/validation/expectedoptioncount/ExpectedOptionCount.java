package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.expectedoptioncount;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        VariantCreateExpectedOptionCountValidator.class,
        VariantUpdateExpectedOptionCountValidator.class,
        VariantPartialUpdateExpectedOptionCountValidator.class
})
@Documented
public @interface ExpectedOptionCount {

    String message() default "{dto_validation.constraints.ExpectedOptionCount}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
