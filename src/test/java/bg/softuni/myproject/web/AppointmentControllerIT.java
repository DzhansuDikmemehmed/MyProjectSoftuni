package bg.softuni.myproject.web;

import bg.softuni.myproject.model.entity.Appointment;
import bg.softuni.myproject.model.entity.enums.AppointmentStatus;
import bg.softuni.myproject.model.entity.enums.TrainingType;
import bg.softuni.myproject.repo.AppointmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AppointmentControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AppointmentRepository appointmentRepository;

    private Long appointmentId;

    @BeforeEach
    public void setUp() throws Exception {
        // Създаване на тестова среща за тестване на update и delete
        Appointment appointment = new Appointment()
                .setCoachName("Test Coach")
                .setType(TrainingType.YOGA)
                .setDescription("Test Description")
                .setAppointmentDateTime(LocalDateTime.now().plusDays(1))
                .setPrice(BigDecimal.valueOf(99.99))
                .setDurationMinutes(60)
                .setMaxParticipants(20)
                .setStatus(AppointmentStatus.ACTIVE)
                .setRemainingSpots(20);

        appointment = appointmentRepository.save(appointment);
        appointmentId = appointment.getId();
    }

    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    void testCreateAppointment() throws Exception {
        mockMvc.perform(
                        post("/add-appointment")
                                .param("coachName", "New Coach")
                                .param("type", TrainingType.CROSSFIT.name())
                                .param("description", "New Test Description")
                                .param("appointmentDateTime", LocalDateTime.now().plusDays(2).toString())
                                .param("price", "120.00")
                                .param("durationMinutes", "90")
                                .param("maxParticipants", "30")
                                .param("status", AppointmentStatus.ACTIVE.name())
                                .param("imageUrl", "https://example.com/image.jpg")
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/appointments-all"));

        // Проверка дали новата среща е съхранена в базата данни
        Optional<Appointment> newAppointment = appointmentRepository.findAll().stream()
                .filter(a -> a.getCoachName().equals("New Coach") && a.getDescription().equals("New Test Description"))
                .findFirst();
        Assertions.assertTrue(newAppointment.isPresent());
    }

    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    void testUpdateAppointment() throws Exception {
        mockMvc.perform(
                        put("/appointments/update/" + appointmentId)
                                .param("coachName", "Updated Coach")
                                .param("type", TrainingType.PILATES.name())
                                .param("description", "Updated Description")
                                .param("appointmentDateTime", LocalDateTime.now().plusDays(3).toString())
                                .param("price", "150.00")
                                .param("durationMinutes", "75")
                                .param("maxParticipants", "25")
                                .param("status", AppointmentStatus.COMPLETED.name())
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/appointments/" + appointmentId));

        // Проверка дали срещата е актуализирана
        Optional<Appointment> updatedAppointment = appointmentRepository.findById(appointmentId);
        Assertions.assertTrue(updatedAppointment.isPresent());

        Appointment appointment = updatedAppointment.get();
        Assertions.assertEquals("Updated Coach", appointment.getCoachName());
        Assertions.assertEquals("Updated Description", appointment.getDescription());
        Assertions.assertEquals(AppointmentStatus.COMPLETED, appointment.getStatus());
    }

    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    void testGetAllAppointments() throws Exception {
        mockMvc.perform(get("/appointments-all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("allAppointments"));
    }

    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    void testAppointmentDetails() throws Exception {
        mockMvc.perform(get("/appointments/" + appointmentId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("appointmentDetails"));
    }

}
