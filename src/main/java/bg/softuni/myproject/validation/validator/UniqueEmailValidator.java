package bg.softuni.myproject.validation.validator;

import bg.softuni.myproject.service.UserService;
import bg.softuni.myproject.validation.annotation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {

    private final UserService userService;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email==null || email.isEmpty()){
            return true;
        }
        return userService.isEmailUnique(email);
    }
}
