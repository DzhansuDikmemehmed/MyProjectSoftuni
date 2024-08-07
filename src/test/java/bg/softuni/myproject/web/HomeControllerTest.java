package bg.softuni.myproject.web;

import bg.softuni.myproject.controller.HomeController;
import bg.softuni.myproject.service.UserService;

import bg.softuni.myproject.service.dto.UserProfileDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService mockUserService;

    private HomeController homeController;

    @BeforeEach
    void setUp() {
        homeController = new HomeController(mockUserService);
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    void testViewAbout() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about"));
    }

//    @Test
//    void testViewProfileNoUser() throws Exception {
//        // Simulate the scenario where the user service returns null
//        when(mockUserService.findUser()).thenReturn(null);
//
//        mockMvc.perform(get("/profile"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("profile"))
//                .andExpect(model().attribute("profileData", nullValue()));
//    }

}
