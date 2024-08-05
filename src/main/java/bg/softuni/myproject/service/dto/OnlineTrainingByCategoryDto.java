package bg.softuni.myproject.service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class OnlineTrainingByCategoryDto {

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


    public OnlineTrainingByCategoryDto(Long id, String title, String description, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public OnlineTrainingByCategoryDto() {
    }

    public Long getId() {
        return id;
    }

    public OnlineTrainingByCategoryDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public OnlineTrainingByCategoryDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OnlineTrainingByCategoryDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OnlineTrainingByCategoryDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
