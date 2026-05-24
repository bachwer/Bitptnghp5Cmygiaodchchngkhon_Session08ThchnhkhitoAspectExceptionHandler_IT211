package org.example.ex5.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LotSizeValidator.class)
@Documented
public @interface ValidLotSize {
    String message() default "Quantity must be a multiple of 100";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

