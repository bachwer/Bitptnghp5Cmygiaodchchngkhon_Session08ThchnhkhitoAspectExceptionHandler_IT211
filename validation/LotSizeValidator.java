package org.example.ex5.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LotSizeValidator implements ConstraintValidator<ValidLotSize, Integer> {
    @Override
    public void initialize(ValidLotSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        // Lot size must be a multiple of 100
        return value > 0 && value % 100 == 0;
    }
}

