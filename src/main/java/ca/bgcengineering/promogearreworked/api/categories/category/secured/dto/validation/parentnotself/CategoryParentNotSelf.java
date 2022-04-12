package ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.parentnotself;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        CategoryUpdateParentNotSelfValidator.class,
        CategoryPartialUpdateParentNotSelfValidator.class
})
@Documented
public @interface CategoryParentNotSelf {

    String message() default "{dto_validation.constraints.CategoryParentNotSelf}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
