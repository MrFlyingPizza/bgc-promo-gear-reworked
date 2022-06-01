package ca.bgcengineering.promogearreworked.api.orders.constraints;

import ca.bgcengineering.promogearreworked.api.orders.general.dto.validator.GeneralOrderBigItemQuantityLimitCreateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.TYPE_USE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        GeneralOrderBigItemQuantityLimitCreateValidator.class
})
public @interface BigItemQuantityLimit {
    String message() default "{dto_validation.constraints.BigItemQuantityLimit}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
