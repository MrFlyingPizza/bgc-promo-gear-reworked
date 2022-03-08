package com.example.bgcpromogearreworked.api.shared.validation.category.annotations;

import com.example.bgcpromogearreworked.api.shared.validation.category.validators.CategoryCreateNameUniqueForParentValidator;
import com.example.bgcpromogearreworked.api.shared.validation.category.validators.CategoryPartialUpdateNameUniqueForParentValidator;
import com.example.bgcpromogearreworked.api.shared.validation.category.validators.CategoryUpdateNameUniqueForParentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        CategoryUpdateNameUniqueForParentValidator.class,
        CategoryCreateNameUniqueForParentValidator.class,
        CategoryPartialUpdateNameUniqueForParentValidator.class
})
@Documented
public @interface CategoryNameUniqueForParent {

    String message() default "{dto_validation.constraints.CategoryNameUniqueForParent}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
