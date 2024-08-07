package bg.softuni.myproject.web;

import bg.softuni.myproject.model.entity.User;
import bg.softuni.myproject.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    void testRegistration() throws Exception {
        String uniqueUsername = "Dzhansu" + System.currentTimeMillis();
        String uniqueEmail = "dzhansu" + System.currentTimeMillis() + "@hmail.com"; // Ensure uniqueness

        mockMvc.perform(
                post("/users/register")
                        .param("username",uniqueUsername)
                        .param("fullName","DzhansuDikmemehmed")
                        .param("email",uniqueEmail)
                        .param("age","20")
                        .param("password","123456")
                        .param("confirmPassword","123456")
                        .param("level","BEGINNER")
                        .param("description","I am new here!")
                        .param("imageUrl","https://i0.wp.com/perform-360.com/wp-content/uploads/2023/11/DSC_0197.jpg")
                        .with(csrf())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));


        Optional<User> byUsername = userRepository.findByUsername(uniqueUsername);

        Assertions.assertTrue(byUsername.isPresent());

        User user = byUsername.get();
        Assertions.assertEquals("DzhansuDikmemehmed", user.getFullName());
        Assertions.assertEquals(uniqueEmail, user.getEmail());


        Assertions.assertTrue(passwordEncoder.matches("123456", user.getPassword()));
    }
}
