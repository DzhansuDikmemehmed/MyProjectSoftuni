package bg.softuni.myproject.service;

import bg.softuni.myproject.model.entity.enums.TrainingType;
import bg.softuni.myproject.service.dto.AddOnlineTrainingDto;
import bg.softuni.myproject.service.dto.AllOnlineTrainingsDto;
import bg.softuni.myproject.service.dto.DetailsOnlineTrainingDto;
import bg.softuni.myproject.service.dto.OnlineTrainingByCategoryDto;

import java.util.List;

public interface OnlineTrainingService {
    Long createOnlineTraining(AddOnlineTrainingDto addOnlineTrainingDto);

    List<AllOnlineTrainingsDto> getAllOnlineTrainings();

    DetailsOnlineTrainingDto getOnlineTrainingDetails(Long id);

    void deleteOnlineTraining(Long id);

//    List<OnlineTrainingByCategoryDto> getOnlineTrainingByCategory(TrainingType category);

    List<OnlineTrainingByCategoryDto> findByType(TrainingType category);
}
