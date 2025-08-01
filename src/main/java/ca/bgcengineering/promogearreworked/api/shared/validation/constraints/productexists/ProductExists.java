package ca.bgcengineering.promogearreworked.api.shared.validation.constraints.productexists;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductExistsValidator.class)
@Documented
public @interface ProductExists {

    String message() default "{dto_validation.constraints.ProductExists}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
