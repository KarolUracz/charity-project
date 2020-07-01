package pl.coderslab.charity.validator;

import pl.coderslab.charity.model.UserForm;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordMatchValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatch {
    String message() default "Pola hasła i potwierdzenia muszą się zgadzać";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
