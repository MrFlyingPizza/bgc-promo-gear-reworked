package com.example.bgcpromogearreworked.api.shared.validation.exists.annotations;

import com.example.bgcpromogearreworked.api.shared.validation.exists.validators.OptionExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OptionExistsValidator.class)
@Documented
public @interface OptionExists {

    String message() default "{dto_validation.constraints.OptionExists}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
