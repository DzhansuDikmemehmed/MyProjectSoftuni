package bg.softuni.myproject.service.dto;

import bg.softuni.myproject.model.entity.enums.AppointmentStatus;
import bg.softuni.myproject.model.entity.enums.TrainingType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DetailsAppointmentDto {

    private long id;
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
    @PositiveOrZero
    private int remainingSpots;

    @NotNull
    private AppointmentStatus status;

    public DetailsAppointmentDto() {
    }


    public long getId() {
        return id;
    }

    public DetailsAppointmentDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DetailsAppointmentDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getCoachName() {
        return coachName;
    }

    public DetailsAppointmentDto setCoachName(String coachName) {
        this.coachName = coachName;
        return this;
    }

    public TrainingType getType() {
        return type;
    }

    public DetailsAppointmentDto setType(TrainingType type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DetailsAppointmentDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public DetailsAppointmentDto setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public DetailsAppointmentDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public DetailsAppointmentDto setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
        return this;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public DetailsAppointmentDto setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
        return this;
    }

    public int getRemainingSpots() {
        return remainingSpots;
    }

    public DetailsAppointmentDto setRemainingSpots(int remainingSpots) {
        this.remainingSpots = remainingSpots;
        return this;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public DetailsAppointmentDto setStatus(AppointmentStatus status) {
        this.status = status;
        return this;
    }
}
