package bg.softuni.myproject.service.dto;

import bg.softuni.myproject.model.entity.enums.AppointmentStatus;
import bg.softuni.myproject.model.entity.enums.TrainingType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddAppointmentDto {
    private Long id;

    private String imageUrl;

    @NotEmpty(message = "Coach Name is required.")
    private String coachName;

    @NotNull
    private TrainingType type;

    @NotEmpty
    private String description;

    @NotNull
    private LocalDateTime appointmentDateTime;

    @Positive
    @NotNull
    private BigDecimal price;

    @Positive
    private int durationMinutes;

    @Positive
    private int maxParticipants;

    @NotNull
    private AppointmentStatus status;


    public Long getId() {
        return id;
    }

    public AddAppointmentDto setId(Long id) {
        this.id = id;
        return this;
    }

    public AddAppointmentDto() {
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public TrainingType getType() {
        return type;
    }

    public void setType(TrainingType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
