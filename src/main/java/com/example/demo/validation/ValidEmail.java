package com.example.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {EmailValidator.class}) // verweist auf die Logik
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    String message() default "E-Mail ist ungültig"; // Fehlermeldung
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

