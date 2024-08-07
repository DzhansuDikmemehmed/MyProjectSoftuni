package bg.softuni.myproject.service.impl;

import bg.softuni.myproject.model.entity.User;
import bg.softuni.myproject.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FitnessUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public FitnessUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(this::mapToUSerDetails)
                .orElseThrow(()-> new UsernameNotFoundException("User with username " + username + " not found!"));
    }

    private UserDetails mapToUSerDetails(User user) {

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(mapToGrantedAuthorities(user))
                .build();
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(User user){
        return user
                .getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                .collect(Collectors.toList());
    }


}
