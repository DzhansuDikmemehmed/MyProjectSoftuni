package bg.softuni.myproject.service.dto;

import bg.softuni.myproject.model.entity.enums.Level;

public class UserProfileDto {

    private String imageUrl;
    private String username;

    private int age;
    private Level level;

    public UserProfileDto() {
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
