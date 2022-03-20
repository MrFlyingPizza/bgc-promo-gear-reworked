package com.example.bgcpromogearreworked.api.shared.validation.constraints.optionvalueexists;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OptionValueExistsValidator.class)
@Documented
public @interface OptionValueExists {

    String message() default "{dto_validation.constraints.OptionValueExists}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
