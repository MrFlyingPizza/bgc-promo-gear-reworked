package com.example.bgcpromogearreworked.api.shared.validation.category.annotations;

import com.example.bgcpromogearreworked.api.shared.validation.category.validators.CategoryCreateParentNotChildValidator;
import com.example.bgcpromogearreworked.api.shared.validation.category.validators.CategoryPartialUpdateParentNotChildValidator;
import com.example.bgcpromogearreworked.api.shared.validation.category.validators.CategoryUpdateParentNotChildValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        CategoryUpdateParentNotChildValidator.class,
        CategoryCreateParentNotChildValidator.class,
        CategoryPartialUpdateParentNotChildValidator.class
})
@Documented
public @interface CategoryParentNotChild {

    String message() default "{dto_validation.constraints.CategoryParentNotChild}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
