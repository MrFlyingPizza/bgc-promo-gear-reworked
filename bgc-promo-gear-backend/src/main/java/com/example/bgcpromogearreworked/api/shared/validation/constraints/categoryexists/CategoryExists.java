package com.example.bgcpromogearreworked.api.shared.validation.constraints.categoryexists;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryExistsValidator.class)
@Documented
public @interface CategoryExists {

    String message() default "{dto_validation.constraints.CategoryExists}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
