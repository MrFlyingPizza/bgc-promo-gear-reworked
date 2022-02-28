package com.example.bgcpromogearreworked.api.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidParentCategoryValidator.class)
@Documented
public @interface ValidParentCategory {

    String message() default "{dto_validation.constraints.ValidParentCategory}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
