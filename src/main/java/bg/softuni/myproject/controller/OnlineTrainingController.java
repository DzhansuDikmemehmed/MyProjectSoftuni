package bg.softuni.myproject.controller;

import bg.softuni.myproject.model.entity.enums.TrainingType;
import bg.softuni.myproject.service.OnlineTrainingService;
import bg.softuni.myproject.service.dto.AddOnlineTrainingDto;
import bg.softuni.myproject.service.dto.OnlineTrainingByCategoryDto;
import bg.softuni.myproject.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class OnlineTrainingController {

    private final OnlineTrainingService onlineTrainingService;

    public OnlineTrainingController(OnlineTrainingService onlineTrainingService) {
        this.onlineTrainingService = onlineTrainingService;
    }

    @ModelAttribute(name = "onlineTrainingData")
    private AddOnlineTrainingDto addOnlineTrainingDto(){
        return new AddOnlineTrainingDto();
    }


    @GetMapping("/add-onlineTraining")
    public String addOnlineTraining(Model model){
        model.addAttribute("allTrainingTypes", TrainingType.values());

        return "add-onlineTraining";
    }

    @PostMapping("/add-onlineTraining")
    public String createOnlineTraining(@Valid AddOnlineTrainingDto addOnlineTrainingDto,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("onlineTrainingData", addOnlineTrainingDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.onlineTrainingData", bindingResult);

            return "redirect:/add-onlineTraining";
        }
        onlineTrainingService.createOnlineTraining(addOnlineTrainingDto);

        return "redirect:/online-allTrainings";

    }

    @GetMapping("/online-allTrainings")
    public String getAllOnlineTrainings(Model model){

        model.addAttribute("allOnlineTrainings", onlineTrainingService.getAllOnlineTrainings());

        return "online-allTrainings";
    }

    @GetMapping("/onlineTraining/{id}")
    public String viewDetails(@PathVariable("id") Long id, Model model){
        model.addAttribute("onlineDetails", onlineTrainingService.getOnlineTrainingDetails(id));

        return "onlineDetails";
    }

    @DeleteMapping("/onlineTraining/delete/{id}")
    public String deleteOnlineTraining(@PathVariable("id") Long id){
        onlineTrainingService.deleteOnlineTraining(id);

        return "redirect:/online-allTrainings";
    }

//    @GetMapping("/onlineTraining/{category}")
//    public String getOnlineTrainingByCategory(@PathVariable TrainingType category, Model model) {
//        String view="";
//        switch (category) {
//            case    YOGA -> view="car";
//            case       PILATES -> view="pilates";
//            case    CROSSFIT-> view="crossfit";
//            case    CARDIO-> view="cardio";
//            case    STRENGTH-> view="strength";
//            case    BODYBUILDING-> view="bodybuilding";
//            case    MEDITATION-> view="meditation";
//            case    HIIT-> view="hiit";
//            case    SPINNING-> view="spinning";
//            case    KICKBOXING-> view="kickboxing";
//            case    BOOTCAMP-> view="bootcamp";
//            case    DANCE-> view="dance";
//        }
//
//        model.addAttribute("onlineTrainingsByCategory", onlineTrainingService.getOnlineTrainingByCategory(category));
//        return view;
//    }

    @GetMapping("/onlineTrainings/{category}")
    public String getOnlineTrainingByCategory(@PathVariable String category, Model model) {
        TrainingType trainingType;
        try {
            // Преобразуване на категорията от String в TrainingType
            trainingType = TrainingType.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Ако категорията не е валидна, върнете изглед за грешка
            model.addAttribute("errorMessage", "Invalid training category: " + category);
            return "error"; // Изглед за грешка
        }

        List<OnlineTrainingByCategoryDto> onlineTrainingsByCategory = onlineTrainingService.findByType(trainingType);

        // Проверка дали има налични тренировки
        if (onlineTrainingsByCategory.isEmpty()) {
            model.addAttribute("infoMessage", "No online trainings available for category: " + trainingType.name());
        } else {
            model.addAttribute("trainingsCategory", onlineTrainingsByCategory);
        }

        model.addAttribute("categoryName", trainingType.name());
        return "categoryView"; // Изглед за категория
    }
}
