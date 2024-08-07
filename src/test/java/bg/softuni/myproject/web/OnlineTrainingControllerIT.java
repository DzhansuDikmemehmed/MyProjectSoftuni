package bg.softuni.myproject.web;

import bg.softuni.myproject.model.entity.OnlineTrainingSession;
import bg.softuni.myproject.model.entity.enums.TrainingType;
import bg.softuni.myproject.repo.OnlineTrainingRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OnlineTrainingControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OnlineTrainingRepository onlineTrainingRepository;

    private Long trainingId;

    @BeforeEach
    public void setUp() {
        OnlineTrainingSession session = new OnlineTrainingSession()
                .setCoachName("Test Coach")
                .setTitle("Test Title")
                .setDescription("Test Description")
                .setVideoUrl("http://example.com/video")
                .setImageUrl("http://example.com/image")
                .setType(TrainingType.YOGA);

        onlineTrainingRepository.save(session);
        trainingId = session.getId();
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

}
