package ca.bgcengineering.promogearreworked.api.transfers.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.TYPE_USE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        SufficientTransferQuantityCreateValidator.class
})
public @interface SufficientTransferQuantity {
    String message() default "{dto_validation.constraints.SufficientTransferQuantity}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
