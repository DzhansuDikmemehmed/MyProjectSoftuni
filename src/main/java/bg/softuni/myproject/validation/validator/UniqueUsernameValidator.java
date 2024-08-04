package bg.softuni.myproject.validation.validator;

import bg.softuni.myproject.service.UserService;
import bg.softuni.myproject.validation.annotation.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {

  private final UserService userService;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null || username.isEmpty()){
            return true;
        }
        return userService.isUsernameUnique(username);
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
