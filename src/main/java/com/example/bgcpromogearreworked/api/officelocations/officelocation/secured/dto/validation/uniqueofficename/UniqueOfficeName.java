package com.example.bgcpromogearreworked.api.officelocations.officelocation.secured.dto.validation.uniqueofficename;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        SecuredOfficeCreateUniqueOfficeNameValidator.class,
        SecuredOfficeUpdateUniqueOfficeNameValidator.class,
        SecuredOfficePartialUpdateUniqueOfficeNameValidator.class
})
public @interface UniqueOfficeName {

    String message() default "{dto_validation.constraints.UniqueOfficeName}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };

}
