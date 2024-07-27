package bg.softuni.myproject.service;

import bg.softuni.myproject.model.entity.User;
import bg.softuni.myproject.service.dto.UserProfileDto;
import bg.softuni.myproject.service.dto.UserRegistrationDto;

public interface UserService {

    void registerUser(UserRegistrationDto userRegistrationDto);

    Long getUserIdByUsername(String username);

    UserProfileDto findUser();
}
