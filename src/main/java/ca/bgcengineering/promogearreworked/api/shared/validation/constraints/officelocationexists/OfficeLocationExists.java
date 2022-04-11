package ca.bgcengineering.promogearreworked.api.shared.validation.constraints.officelocationexists;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OfficeLocationExistsValidator.class)
public @interface OfficeLocationExists {

    String message() default "{dto_validation.constraints.OfficeLocationExists}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
