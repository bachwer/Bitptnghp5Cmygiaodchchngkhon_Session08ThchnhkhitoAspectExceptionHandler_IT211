package org.example.ex5.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StockCodeValidator implements ConstraintValidator<ValidStockCode, String> {
    @Override
    public void initialize(ValidStockCode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        // Stock code must be exactly 3 uppercase letters
        return value.matches("^[A-Z]{3}$");
    }
}

