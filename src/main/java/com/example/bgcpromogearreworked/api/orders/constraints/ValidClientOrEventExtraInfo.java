package com.example.bgcpromogearreworked.api.orders.constraints;

import com.example.bgcpromogearreworked.api.orders.general.dto.validator.GeneralValidClientOrEventExtraInfoOrderCreateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.TYPE_USE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        GeneralValidClientOrEventExtraInfoOrderCreateValidator.class
})
public @interface ValidClientOrEventExtraInfo {
    String message() default "{dto_validation.constraints.ValidExtraInfo}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
