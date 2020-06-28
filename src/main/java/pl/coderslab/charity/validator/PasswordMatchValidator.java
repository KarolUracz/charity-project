package pl.coderslab.charity.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, String> {
    private String firstPassword;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        firstPassword = constraintAnnotation.password();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals(firstPassword);
    }
}
