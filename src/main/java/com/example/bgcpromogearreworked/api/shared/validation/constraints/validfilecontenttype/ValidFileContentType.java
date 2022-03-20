package com.example.bgcpromogearreworked.api.shared.validation.constraints.validfilecontenttype;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidFileContentTypeValidator.class)
@Documented
public @interface ValidFileContentType {

    String message() default "{dto_validation.constraints.ValidFileContentType}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String[] acceptedMediaTypes();

}
