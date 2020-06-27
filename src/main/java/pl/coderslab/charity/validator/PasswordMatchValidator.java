package pl.coderslab.charity.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, String> {
    private String firstPassword;
    private String secondPassword;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        firstPassword = constraintAnnotation.first();
        secondPassword = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return firstPassword.equals(secondPassword);
    }
}
