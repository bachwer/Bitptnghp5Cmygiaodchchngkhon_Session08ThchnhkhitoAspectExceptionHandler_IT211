package org.example.ex5.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StockCodeValidator.class)
@Documented
public @interface ValidStockCode {
    String message() default "Stock code must be exactly 3 uppercase letters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

