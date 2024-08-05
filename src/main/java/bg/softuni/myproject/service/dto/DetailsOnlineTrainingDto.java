package bg.softuni.myproject.service.dto;

import bg.softuni.myproject.model.entity.enums.TrainingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DetailsOnlineTrainingDto {
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


    public DetailsOnlineTrainingDto() {
    }

    public Long getId() {
        return id;
    }

    public DetailsOnlineTrainingDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCoachName() {
        return coachName;
    }

    public DetailsOnlineTrainingDto setCoachName(String coachName) {
        this.coachName = coachName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public DetailsOnlineTrainingDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DetailsOnlineTrainingDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public DetailsOnlineTrainingDto setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DetailsOnlineTrainingDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public TrainingType getType() {
        return type;
    }

    public DetailsOnlineTrainingDto setType(TrainingType type) {
        this.type = type;
        return this;
    }
}
