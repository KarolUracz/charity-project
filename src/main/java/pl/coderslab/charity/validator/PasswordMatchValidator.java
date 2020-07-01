package pl.coderslab.charity.validator;

import pl.coderslab.charity.model.UserForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserForm> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserForm userForm, ConstraintValidatorContext context) {
        return userForm.getPassword().equals(userForm.getPasswordConfirm());
    }
}
