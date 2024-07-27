package bg.softuni.myproject.service.impl;

import bg.softuni.myproject.model.entity.User;
import bg.softuni.myproject.repo.UserRepository;
import bg.softuni.myproject.service.UserService;
import bg.softuni.myproject.service.dto.UserProfileDto;
import bg.softuni.myproject.service.dto.UserRegistrationDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Override
    public Long getUserIdByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .map(user-> user.getId())
                .orElseThrow(()-> new RuntimeException("User not found with username: " + username));
    }

    @Override
    public UserProfileDto findUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            // Fetch the user from the repository
            User user = userRepository.findByUsername(username).orElse(null);

            if (user != null) {
                // Convert User entity to UserProfileDto
                return modelMapper.map(user, UserProfileDto.class);
            }
        }
        return null;
    }


    private User map(UserRegistrationDto userRegistrationDto) {
        User mappedUser = modelMapper.map(userRegistrationDto, User.class);
        mappedUser.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));

        return mappedUser;

    }
}
