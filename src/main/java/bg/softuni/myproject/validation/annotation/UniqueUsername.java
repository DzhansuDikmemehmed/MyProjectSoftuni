package bg.softuni.myproject.validation.annotation;

import bg.softuni.myproject.validation.validator.UniqueUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername {
    String message() default "Username must be unique! Please change your username!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
