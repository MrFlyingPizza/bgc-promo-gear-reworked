package com.example.bgcpromogearreworked.api.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OptionValueExistsValidator.class)
@Documented
public @interface OptionValueExists {

    String message() default "{dto_validation.constraints.OptionValueExists}";

    // the field name in the validated object for the product ID.
    String productIdFieldName() default "productId";

    // the field name in the validated object for the option name.
    String optionNameFieldName() default "name";

    // the field name in the validated object for the option value.
    String optionValueFieldName() default "value";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
