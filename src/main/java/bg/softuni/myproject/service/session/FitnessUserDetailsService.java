package bg.softuni.myproject.service.session;

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
    private final ModelMapper modelMapper;

    public FitnessUserDetailsService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(this::mapToUSerDetails)
                .orElseThrow(()-> new UsernameNotFoundException("User with username " + username + " not foun!"));
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
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                .collect(Collectors.toList());
    }


}
