package bg.softuni.myproject.service.dto;

import bg.softuni.myproject.model.entity.enums.TrainingType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AllAppointmentsDto {


    private long id;
    private String imageUrl;

    @NotEmpty(message = "Coach Name is required.")
    private String coachName;

    @NotNull
    private TrainingType type;

    @NotNull
    private LocalDateTime appointmentDateTime;

    @Positive
    @NotNull
    private BigDecimal price;

    public AllAppointmentsDto() {
    }

    public long getId() {
        return id;
    }

    public AllAppointmentsDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AllAppointmentsDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getCoachName() {
        return coachName;
    }

    public AllAppointmentsDto setCoachName(String coachName) {
        this.coachName = coachName;
        return this;
    }

    public TrainingType getType() {
        return type;
    }

    public AllAppointmentsDto setType(TrainingType type) {
        this.type = type;
        return this;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public AllAppointmentsDto setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AllAppointmentsDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
