package bg.softuni.myproject.service.impl;

import bg.softuni.myproject.model.entity.OnlineTrainingSession;
import bg.softuni.myproject.model.entity.enums.TrainingType;
import bg.softuni.myproject.repo.OnlineTrainingRepository;
import bg.softuni.myproject.service.OnlineTrainingService;
import bg.softuni.myproject.service.dto.*;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OnlineTrainingServiceImpl implements OnlineTrainingService {
    private Logger LOGGER = LoggerFactory.getLogger(OnlineTrainingServiceImpl.class);

    private final ModelMapper modelMapper;
    private final OnlineTrainingRepository onlineTrainingRepository;

    private final RestClient restClient;




    public OnlineTrainingServiceImpl(ModelMapper modelMapper, OnlineTrainingRepository onlineTrainingRepository,
                                    @Qualifier("trainingsRestClient") RestClient restClient) {
        this.modelMapper = modelMapper;
        this.onlineTrainingRepository = onlineTrainingRepository;

        this.restClient = restClient;
    }

    @Override
    public void createOnlineTraining(AddOnlineTrainingDto addOnlineTrainingDto) {
        LOGGER.info("Creating new online training...");

        restClient
                .post()
                .uri("http://localhost:8081/add-onlineTraining")
                .accept(MediaType.APPLICATION_JSON)
                .body(addOnlineTrainingDto)
                .retrieve();

//        OnlineTrainingSession onlineTrainingSession = modelMapper.map(addOnlineTrainingDto, OnlineTrainingSession.class);
        //  return   onlineTrainingRepository.save(onlineTrainingSession).getId();


    }

    @Override
    public List<AllOnlineTrainingsDto> getAllOnlineTrainings() {
//        return onlineTrainingRepository.findAll()
//                .stream()
//                .map(r -> modelMapper.map(r, AllOnlineTrainingsDto.class))
//                .collect(Collectors.toList());
        LOGGER.info("Get all online trainings...");

        return restClient
                .get()
                .uri("http://localhost:8081/online-allTrainings")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    @Override
    public DetailsOnlineTrainingDto getOnlineTrainingDetails(Long id) {
//        Optional<OnlineTrainingSession> onlineTrainingSessionOpt = onlineTrainingRepository.findById(id);
//        if (onlineTrainingSessionOpt.isEmpty()) {
//            throw new ObjectNotFoundException("Online training not found!", id);
//        }
//
//        DetailsOnlineTrainingDto dto = modelMapper.map(onlineTrainingSessionOpt.get(), DetailsOnlineTrainingDto.class);
//        return dto;

        return restClient
                .get()
                .uri("http://localhost:8081/onlineTraining/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(DetailsOnlineTrainingDto.class);
    }

    @Override
    public void deleteOnlineTraining(Long id) {

//        onlineTrainingRepository.deleteById(id);
        LOGGER.info("Successfully deleted training with id: " + id);
        restClient
                .delete()
                .uri("http://localhost:8081/onlineTraining/delete/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();

    }

    @Override
    public List<OnlineTrainingByCategoryDto> findByType(TrainingType category) {
       // return onlineTrainingRepository.findByType(category);

    return restClient
            .get()
            .uri("http://localhost:8081/onlineTrainings/{category}", category.name())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(new ParameterizedTypeReference<List<OnlineTrainingByCategoryDto>>() {
            });
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

