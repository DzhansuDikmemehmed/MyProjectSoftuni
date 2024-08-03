package bg.softuni.myproject.service;

import bg.softuni.myproject.model.entity.enums.AppointmentStatus;
import bg.softuni.myproject.model.entity.enums.TrainingType;
import bg.softuni.myproject.service.dto.AddAppointmentDto;
import bg.softuni.myproject.service.dto.AllAppointmentsDto;
import bg.softuni.myproject.service.dto.DetailsAppointmentDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    Long createAppointment(AddAppointmentDto addAppointmentDto);

    List<AllAppointmentsDto> getAllAppointments();

    DetailsAppointmentDto getAppointmentDetails(Long id);

    boolean registerForAppointment(Long appointmentId, Long userId);

    boolean unregisterFromAppointment(Long appointmentId, Long userId);

    void deleteAppointment(long appointmentId);

    boolean isUserRegistered(Long appointmentId, Long userId);

    public boolean updateAppointment(Long id, String coachName, LocalDateTime appointmentDateTime, BigDecimal price, int durationMinutes, int maxParticipants, String description, AppointmentStatus status, TrainingType trainingType);

}