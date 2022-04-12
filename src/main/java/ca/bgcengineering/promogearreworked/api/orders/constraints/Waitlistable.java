package ca.bgcengineering.promogearreworked.api.orders.constraints;

import ca.bgcengineering.promogearreworked.api.orders.general.dto.validator.GeneralOrderItemWaitlistableCreateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.TYPE_USE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        GeneralOrderItemWaitlistableCreateValidator.class
})
public @interface Waitlistable {
    String message() default "{dto_validation.constraints.Waitlistable}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
