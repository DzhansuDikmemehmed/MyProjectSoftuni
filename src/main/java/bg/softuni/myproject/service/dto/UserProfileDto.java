package bg.softuni.myproject.service.dto;

import bg.softuni.myproject.model.entity.enums.Level;

public class UserProfileDto {

    private String imageUrl;
    private String username;

    private String fullName;

    private int age;
    private Level level;

    private String description;
    public UserProfileDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public UserProfileDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserProfileDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserProfileDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserProfileDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserProfileDto setAge(int age) {
        this.age = age;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public UserProfileDto setLevel(Level level) {
        this.level = level;
        return this;
    }
}
