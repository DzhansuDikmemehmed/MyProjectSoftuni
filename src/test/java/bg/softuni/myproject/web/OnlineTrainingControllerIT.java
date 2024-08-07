package bg.softuni.myproject.web;

import bg.softuni.myproject.model.entity.OnlineTrainingSession;
import bg.softuni.myproject.model.entity.enums.TrainingType;
import bg.softuni.myproject.repo.OnlineTrainingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.EnumSet.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OnlineTrainingControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OnlineTrainingRepository onlineTrainingRepository;

    @BeforeEach
    public void setUp() {
        onlineTrainingRepository.deleteAll(); // Clean up repository

        OnlineTrainingSession session = new OnlineTrainingSession()
                .setCoachName("Test Coach")
                .setTitle("Test Title")
                .setDescription("Test Description")
                .setVideoUrl("http://example.com/video")
                .setImageUrl("http://example.com/image")
                .setType(TrainingType.YOGA);

        onlineTrainingRepository.save(session);
    }

    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    void testAddOnlineTraining() throws Exception {
        mockMvc.perform(get("/add-onlineTraining"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-onlineTraining"))
                .andExpect(model().attributeExists("allTrainingTypes"));
    }

    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    void testGetAllOnlineTrainings() throws Exception {
        mockMvc.perform(get("/online-allTrainings"))
                .andExpect(status().isOk())
                .andExpect(view().name("online-allTrainings"))
                .andExpect(model().attributeExists("allOnlineTrainings"));
    }



    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    void testGetOnlineTrainingByCategory_InvalidCategory() throws Exception {
        mockMvc.perform(get("/onlineTrainings/{category}", "INVALID_CATEGORY"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attribute("errorMessage", "Invalid training category: INVALID_CATEGORY"));
    }

}





