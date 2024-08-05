package bg.softuni.myproject.model.entity;

import bg.softuni.myproject.model.entity.enums.TrainingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "online_training_session")
public class OnlineTrainingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String coachName;
    @NotBlank
    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column
    private String videoUrl;

    @Column
    private String imageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TrainingType type;



    public OnlineTrainingSession() {
    }



    public Long getId() {
        return id;
    }

    public String getCoachName() {
        return coachName;
    }

    public OnlineTrainingSession setCoachName(String coachName) {
        this.coachName = coachName;
        return this;
    }

    public OnlineTrainingSession setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public OnlineTrainingSession setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OnlineTrainingSession setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public OnlineTrainingSession setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OnlineTrainingSession setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public TrainingType getType() {
        return type;
    }

    public OnlineTrainingSession setType(TrainingType type) {
        this.type = type;
        return this;
    }
}
