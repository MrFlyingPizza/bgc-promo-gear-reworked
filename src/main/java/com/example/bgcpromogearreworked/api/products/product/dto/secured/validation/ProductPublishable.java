package com.example.bgcpromogearreworked.api.products.product.dto.secured.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductPublishableValidator.class)
@Documented
public @interface ProductPublishable {

    String message() default "{dto_validation.constraints.ProductPublishable}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}