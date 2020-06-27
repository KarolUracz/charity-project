package pl.coderslab.charity.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordMatchValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatch {
    String first();
    String second();
    String message() default "Pola muszą się zgadzać";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
