package ca.bgcengineering.promogearreworked.api.shared.validation.constraints.userexists;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserExistsValidator.class)
@Target(ElementType.FIELD)
@Documented
public @interface UserExists {

    String message() default "{dto_validation.constraints.UserExists}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
