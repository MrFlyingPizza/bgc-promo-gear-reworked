package ca.bgcengineering.promogearreworked.api.shared.validation.constraints.userexists;

import ca.bgcengineering.promogearreworked.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserExistsValidator implements ConstraintValidator<UserExists, Long> {

    @Autowired
    private UserRepository userRepo;

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext constraintValidatorContext) {
        return userId == null || userRepo.existsById(userId);
    }

}
