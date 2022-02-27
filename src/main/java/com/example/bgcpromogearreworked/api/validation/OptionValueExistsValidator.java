package com.example.bgcpromogearreworked.api.validation;

import com.example.bgcpromogearreworked.api.product.OptionValueId;
import com.example.bgcpromogearreworked.api.product.OptionValueRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionValueExistsValidator implements ConstraintValidator<OptionValueExists, Object> {

    private String productIdFieldName;
    private String optionNameFieldName;
    private String optionValueFieldName;

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Override
    public void initialize(OptionValueExists constraintAnnotation) {
        this.productIdFieldName = constraintAnnotation.productIdFieldName();
        this.optionNameFieldName = constraintAnnotation.optionNameFieldName();
        this.optionValueFieldName = constraintAnnotation.optionValueFieldName();
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Class<?> optionValueClass = o.getClass();
        Long productId = (Long) optionValueClass.getField(productIdFieldName).get(o);
        String optionName = (String) optionValueClass.getField(optionNameFieldName).get(o);
        String optionValue = (String) optionValueClass.getField(optionValueFieldName).get(o);
        return optionValueRepo.existsById(new OptionValueId(productId, optionName, optionValue));
    }
}
