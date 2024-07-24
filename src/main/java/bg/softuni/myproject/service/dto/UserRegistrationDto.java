package bg.softuni.myproject.service.dto;

import bg.softuni.myproject.model.entity.Level;
import jakarta.validation.constraints.*;

public class UserRegistrationDto {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotEmpty
    @Size(min = 5, max = 20)
    private String fullName;

    @Email
    private String email;

    @Min(0)
    @Max(90)
    private Integer age;

    @Size(min = 5)
    @NotEmpty
    private String password;
    private String confirmPassword;

    private Level level;


    public UserRegistrationDto() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationDto setUsername(String username) {
        this.username = username;
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
