// Benutzerdefinierte Annotation

package com.example.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented // für die generierte Dokumentation -> @ValidEmail erscheint in der Dokumentation als Grund der Validierung
@Constraint(validatedBy = {EmailValidator.class}) // verweist auf die Logik
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    String message() default "Ungültige E-Mail-Adresse"; // Fehlermeldung
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

