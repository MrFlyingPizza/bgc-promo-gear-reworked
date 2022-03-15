package com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured.validation.uniquenamevalue;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        OptionValueCreateUniqueNameValueValidator.class,
        OptionValueUpdateUniqueNameValueValidator.class,
        OptionValuePartialUpdateUniqueNameValueValidator.class
})
@Documented
public @interface UniqueNameValue {

    String message() default "{dto_validation.constraints.UniqueOptionNameValue}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
