package ca.bgcengineering.promogearreworked.api.shared.validation.constraints.validfilesize;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidFileSizeValidator implements ConstraintValidator<ValidFileSize, MultipartFile> {

    private int max;

    @Override
    public void initialize(ValidFileSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file == null) return true;
        return file.getSize() <= this.max;
    }
}
