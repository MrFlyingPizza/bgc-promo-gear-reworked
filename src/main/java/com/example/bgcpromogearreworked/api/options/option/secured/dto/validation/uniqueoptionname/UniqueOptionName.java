package com.example.bgcpromogearreworked.api.options.option.secured.dto.validation.uniqueoptionname;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OptionCreateUniqueNameValidator.class)
@Documented
public @interface UniqueOptionName {

    String message() default "{dto_validation.constraints.UniqueOptionName}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
