package bg.softuni.myproject.model.entity;

import bg.softuni.myproject.model.entity.enums.AppointmentStatus;
import bg.softuni.myproject.model.entity.enums.TrainingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String imageUrl;
    @NotEmpty
    private String coachName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TrainingType type;

    @NotEmpty
    private String description;

    @NotNull
    private LocalDateTime appointmentDateTime;

    @Positive
    private BigDecimal price;

    @Positive
    private int durationMinutes;

    @Positive
    private int maxParticipants;


    @NotNull
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Positive
    private int remainingSpots;

    public Appointment() {
    }

    public int getRemainingSpots() {
        return remainingSpots;
    }

    public Appointment setRemainingSpots(int remainingSpots) {
        this.remainingSpots = remainingSpots;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Appointment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Appointment setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getCoachName() {
        return coachName;
    }

    public Appointment setCoachName(String coachName) {
        this.coachName = coachName;
        return this;
    }

    public TrainingType getType() {
        return type;
    }

    public Appointment setType(TrainingType type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Appointment setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public Appointment setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Appointment setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public Appointment setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
        return this;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public Appointment setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
        return this;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public Appointment setStatus(AppointmentStatus status) {
        this.status = status;
        return this;
    }
}
