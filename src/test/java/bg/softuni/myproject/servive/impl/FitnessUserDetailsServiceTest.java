package bg.softuni.myproject.servive.impl;

import bg.softuni.myproject.model.entity.Role;
import bg.softuni.myproject.model.entity.User;
import bg.softuni.myproject.model.entity.enums.Level;
import bg.softuni.myproject.model.entity.enums.UserRoles;
import bg.softuni.myproject.repo.UserRepository;
import bg.softuni.myproject.service.impl.FitnessUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class FitnessUserDetailsServiceTest {

    private static final String TEST_USERNAME = "Dzhansu";
    private static final String NOT_EXISTING_USERNAME = "Nobody";

    private FitnessUserDetailsService toTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new FitnessUserDetailsService(mockUserRepository);
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        //Arrange
        User testUser = new User()
                .setAge(20)
                .setFullName("DzhansuDikmemehmed")
                .setLevel(Level.BEGINNER)
                .setPassword("123456")
                .setUsername(TEST_USERNAME)
                .setDescription("I am new here!")
                .setImageUrl("https://i0.wp.com/perform-360.com/wp-content/uploads/2023/11/DSC_0197.jpg")
                .setRoles(List.of(
                        new Role().setRole(UserRoles.ADMIN),
                        new Role().setRole(UserRoles.USER)
                ));

        Mockito.when(mockUserRepository.findByUsername(TEST_USERNAME))
                .thenReturn(Optional.of(testUser));

        //Act
        UserDetails userDetails = toTest.loadUserByUsername(TEST_USERNAME);

        //Assert
        Assertions.assertEquals(TEST_USERNAME, userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());

        var expectedRoles = testUser.getRoles().stream().map(Role::getRole).map(r -> "ROLE_" + r).toList();
        var actualRoles = userDetails.getAuthorities().stream().map(a -> a.getAuthority()).toList();
        Assertions.assertEquals(expectedRoles, actualRoles);

    }


    @Test
    void testLoadUserByUsername_UserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTING_USERNAME)
        );
    }
}
