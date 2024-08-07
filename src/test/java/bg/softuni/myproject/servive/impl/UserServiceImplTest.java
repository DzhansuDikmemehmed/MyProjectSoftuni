package bg.softuni.myproject.servive.impl;

import bg.softuni.myproject.model.entity.User;
import bg.softuni.myproject.repo.UserRepository;
import bg.softuni.myproject.service.dto.UserRegistrationDto;
import bg.softuni.myproject.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static bg.softuni.myproject.model.entity.enums.Level.BEGINNER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    private UserServiceImpl toTest;

    @Captor
    private ArgumentCaptor<User> userEntityCaptor;

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    void setUp() {
        toTest = new UserServiceImpl(
                mockUserRepository,
                new ModelMapper(),
                mockPasswordEncoder
        );
    }

    @Test
    void testUserRegistration(){
      //Arrange
        UserRegistrationDto userRegistrationDto =
                new UserRegistrationDto()
                        .setUsername("DzhansuD")
                        .setFullName("DzhansuDikmemehmed")
                        .setEmail("dzhansu@hmail.com")
                        .setAge(20)
                        .setPassword("123456")
                        .setConfirmPassword("123456")
                        .setLevel(BEGINNER)
                        .setDescription("I am new here!")
                        .setImageUrl("https://i0.wp.com/perform-360.com/wp-content/uploads/2023/11/DSC_0197.jpg");

        String expectedEncodedPassword = userRegistrationDto.getPassword();

        when(mockPasswordEncoder.encode(userRegistrationDto.getPassword()))
                .thenReturn(expectedEncodedPassword);
        //Act
        toTest.registerUser(userRegistrationDto);
        //Assert
        verify(mockUserRepository).save(userEntityCaptor.capture());
        User actualSavedEntity = userEntityCaptor.getValue();
        Assertions.assertNotNull(mockUserRepository);
        Assertions.assertEquals(userRegistrationDto.getUsername(),actualSavedEntity.getUsername());
        Assertions.assertEquals(userRegistrationDto.getFullName(),actualSavedEntity.getFullName());
        Assertions.assertEquals(userRegistrationDto.getEmail(),actualSavedEntity.getEmail());
        Assertions.assertEquals(userRegistrationDto.getAge(),actualSavedEntity.getAge());
        Assertions.assertEquals(userRegistrationDto.getPassword(),actualSavedEntity.getPassword());
        Assertions.assertEquals(userRegistrationDto.getLevel(),actualSavedEntity.getLevel());
        Assertions.assertEquals(userRegistrationDto.getDescription(),actualSavedEntity.getDescription());
        Assertions.assertEquals(userRegistrationDto.getImageUrl(),actualSavedEntity.getImageUrl());


    }


}
