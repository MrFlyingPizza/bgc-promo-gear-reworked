package ca.bgcengineering.promogearreworked.api.shared.validation.constraints.productimageexists;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductImageExistsValidator.class)
public @interface ProductImageExists {

    String message() default "{dto_validation.constraints.ProductImageExists}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
