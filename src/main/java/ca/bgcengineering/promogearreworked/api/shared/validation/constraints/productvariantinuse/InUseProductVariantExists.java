package ca.bgcengineering.promogearreworked.api.shared.validation.constraints.productvariantinuse;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InUseProductVariantExistsValidator.class)
@Documented
public @interface InUseProductVariantExists {
    // this uses the same message as ProductVariantExists because it is meant to not expose the underlying hidden variants.
    String message() default "{dto_validation.constraints.ProductVariantExists}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
