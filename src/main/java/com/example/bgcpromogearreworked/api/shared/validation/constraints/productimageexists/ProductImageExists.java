package com.example.bgcpromogearreworked.api.shared.validation.constraints.productimageexists;

import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE, ElementType.TYPE_USE})
public @interface ProductImageExists {

    String message() default "{dto_validation.constraints.ProductImageExists}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
