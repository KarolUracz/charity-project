package pl.coderslab.charity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.ScriptAssert;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@ScriptAssert(lang = "javascript", script = "_this.password === _this.passwordConfirm", message = "Hasła muszą się zgadzać")
public class UserForm {
    @Email
    private String username;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!*@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Hasło musi zawierać co najmniej jedną dużą literę, jedną małą, jedną cyfrę oraz znak specjalny")
    private String password;
//    @PasswordMatch(password = "")
    private String passwordConfirm;
}
