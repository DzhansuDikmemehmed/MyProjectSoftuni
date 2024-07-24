package bg.softuni.myproject.service;

import bg.softuni.myproject.service.dto.UserRegistrationDto;

public interface UserService {

    void registerUser(UserRegistrationDto userRegistrationDto);
}
