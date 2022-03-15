package com.example.bgcpromogearreworked.api.products.variant.dto.secured.validation.optioncountmatch;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
})
@Documented
public @interface ExpectedOptionCount {

    String message() default "{dto_validation.constraints.ExpectedOptionCount}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
