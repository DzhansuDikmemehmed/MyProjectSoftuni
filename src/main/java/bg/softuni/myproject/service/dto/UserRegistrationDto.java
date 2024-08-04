package bg.softuni.myproject.service.dto;

import bg.softuni.myproject.model.entity.enums.Level;
import bg.softuni.myproject.validation.annotation.UniqueEmail;
import bg.softuni.myproject.validation.annotation.UniqueUsername;
import bg.softuni.myproject.validation.annotation.ValidatePasswords;
import jakarta.validation.constraints.*;

@ValidatePasswords
public class UserRegistrationDto {

    @NotBlank(message = "Username cannot be blank.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long.")
    @UniqueUsername
    private String username;

    @NotEmpty(message = "Full name cannot be empty.")
    @Size(min = 5, max = 20, message = "Full name must be between 5 and 20 characters long.")
    private String fullName;

    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email must be valid and properly formatted.")
    @UniqueEmail
    private String email;

    @Min(value = 1, message = "Age must be at least 1.")
    @Max(value = 90, message = "Age must be no more than 90.")
    private Integer age;

    @Size(min = 5, max = 200, message = "Password must be between 5 and 200 characters long.")
    @NotEmpty(message = "Password cannot be empty.")
    private String password;

    @NotEmpty(message = "Confirm password cannot be empty.")
    private String confirmPassword;

    private Level level;

    private String description;

    private String imageUrl;


    public UserRegistrationDto() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserRegistrationDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserRegistrationDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegistrationDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserRegistrationDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public UserRegistrationDto setLevel(Level level) {
        this.level = level;
        return this;
    }
}
