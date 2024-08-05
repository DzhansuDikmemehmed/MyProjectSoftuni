package bg.softuni.myproject.service.dto;

import bg.softuni.myproject.model.entity.enums.TrainingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AllOnlineTrainingsDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;
    @Column
    private String imageUrl;




    public AllOnlineTrainingsDto() {
    }

    public Long getId() {
        return id;
    }

    public AllOnlineTrainingsDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AllOnlineTrainingsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AllOnlineTrainingsDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AllOnlineTrainingsDto setTitle(String title) {
        this.title = title;
        return this;
    }


}
