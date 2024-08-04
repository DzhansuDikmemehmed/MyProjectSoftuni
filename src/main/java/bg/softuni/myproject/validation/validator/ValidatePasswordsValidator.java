package bg.softuni.myproject.validation.validator;

import bg.softuni.myproject.service.dto.UserRegistrationDto;
import bg.softuni.myproject.validation.annotation.ValidatePasswords;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.stereotype.Component;


@Component
public class ValidatePasswordsValidator implements ConstraintValidator<ValidatePasswords, UserRegistrationDto> {

    private String message;
    @Override
    public void initialize(ValidatePasswords constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    this.message= constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UserRegistrationDto dto, ConstraintValidatorContext context) {


       if (dto.getPassword() == null || dto.getConfirmPassword()==null){
           return true;
       }

        boolean isValid = dto.getPassword().equals(dto.getConfirmPassword());
       if (!isValid){
           context.unwrap(HibernateConstraintValidatorContext.class)
                   .buildConstraintViolationWithTemplate(message)
                   .addPropertyNode("confirmPassword")
                   .addConstraintViolation()
                   .disableDefaultConstraintViolation();


       }
        return isValid;
    }
}
