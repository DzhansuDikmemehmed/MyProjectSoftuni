package bg.softuni.myproject.service.impl;

import bg.softuni.myproject.model.entity.OnlineTrainingSession;
import bg.softuni.myproject.model.entity.enums.TrainingType;
import bg.softuni.myproject.repo.OnlineTrainingRepository;
import bg.softuni.myproject.service.OnlineTrainingService;
import bg.softuni.myproject.service.dto.*;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OnlineTrainingServiceImpl implements OnlineTrainingService {
    private final ModelMapper modelMapper;
    private final OnlineTrainingRepository onlineTrainingRepository;

    private final CategoryRepository categoryRepository;

    public OnlineTrainingServiceImpl(ModelMapper modelMapper, OnlineTrainingRepository onlineTrainingRepository, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.onlineTrainingRepository = onlineTrainingRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Long createOnlineTraining(AddOnlineTrainingDto addOnlineTrainingDto) {
        OnlineTrainingSession onlineTrainingSession = modelMapper.map(addOnlineTrainingDto, OnlineTrainingSession.class);


        return   onlineTrainingRepository.save(onlineTrainingSession).getId();

    }

    @Override
    public List<AllOnlineTrainingsDto> getAllOnlineTrainings() {
        return onlineTrainingRepository.findAll()
                .stream()
                .map(r -> modelMapper.map(r, AllOnlineTrainingsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DetailsOnlineTrainingDto getOnlineTrainingDetails(Long id) {
        Optional<OnlineTrainingSession> onlineTrainingSessionOpt = onlineTrainingRepository.findById(id);
        if (onlineTrainingSessionOpt.isEmpty()) {
            throw new ObjectNotFoundException("Online training not found!", id);
        }

        DetailsOnlineTrainingDto dto = modelMapper.map(onlineTrainingSessionOpt.get(), DetailsOnlineTrainingDto.class);
        return dto;
    }

    @Override
    public void deleteOnlineTraining(Long id) {
        onlineTrainingRepository.deleteById(id);
    }

    @Override
    public List<OnlineTrainingByCategoryDto> findByType(TrainingType category) {
        return onlineTrainingRepository.findByType(category);
    }

//    @Override
//    public List<OnlineTrainingByCategoryDto> getOnlineTrainingByCategory(TrainingType category) {
//        List<OnlineTrainingSession> allByCategoriesName = onlineTrainingRepository.findAllByCategory_Name(category);
//
//        return allByCategoriesName.stream()
//                .map(training-> modelMapper.map(training, OnlineTrainingByCategoryDto.class))
//                .toList();
//    }

}

