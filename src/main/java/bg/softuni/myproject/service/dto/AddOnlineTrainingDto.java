package bg.softuni.myproject.service.dto;

import bg.softuni.myproject.model.entity.enums.TrainingType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddOnlineTrainingDto {

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




    public AddOnlineTrainingDto() {
    }



    public String getCoachName() {
        return coachName;
    }

    public AddOnlineTrainingDto setCoachName(String coachName) {
        this.coachName = coachName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AddOnlineTrainingDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOnlineTrainingDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public AddOnlineTrainingDto setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddOnlineTrainingDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public TrainingType getType() {
        return type;
    }

    public AddOnlineTrainingDto setType(TrainingType type) {
        this.type = type;
        return this;
    }
}
