package bg.softuni.myproject.service.impl;

import bg.softuni.myproject.model.entity.User;
import bg.softuni.myproject.repo.UserRepository;
import bg.softuni.myproject.service.UserService;
import bg.softuni.myproject.service.dto.UserRegistrationDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {

        userRepository.save(map(userRegistrationDto));

    }

    private User map(UserRegistrationDto userRegistrationDto) {
        User mappedUser = modelMapper.map(userRegistrationDto, User.class);
        mappedUser.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));

        return mappedUser;

    }
}
