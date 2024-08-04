package bg.softuni.myproject.validation.annotation;

import bg.softuni.myproject.validation.validator.ValidatePasswordsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ValidatePasswordsValidator.class)
public @interface ValidatePasswords {

    String message() default "Password and Confirm password must be same!";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default  {};

}
