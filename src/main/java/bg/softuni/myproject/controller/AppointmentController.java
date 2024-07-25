package bg.softuni.myproject.controller;

import bg.softuni.myproject.model.entity.enums.AppointmentStatus;
import bg.softuni.myproject.model.entity.enums.TrainingType;
import bg.softuni.myproject.service.AppointmentService;
import bg.softuni.myproject.service.dto.AddAppointmentDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @ModelAttribute(name = "appointmentData")
    public AddAppointmentDto addAppointmentDto(){
        return new AddAppointmentDto();
    }

    @GetMapping("/add-appointment")
    public String newAppointment(Model model){

        model.addAttribute("allTrainingTypes", TrainingType.values());
        model.addAttribute("allStatusTypes", AppointmentStatus.values());

        return "add-appointment";
    }

    @PostMapping("/add-appointment")
    public String createAppointment(@Valid AddAppointmentDto addAppointmentDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("appointmentData", addAppointmentDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.appointmentData", bindingResult);

            return "redirect:/add-appointment";
        }
        appointmentService.createAppointment(addAppointmentDto);

        return "redirect:/appointments/all";

    }


}
