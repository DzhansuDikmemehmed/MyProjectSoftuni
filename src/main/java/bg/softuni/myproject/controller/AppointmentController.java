package bg.softuni.myproject.controller;

import bg.softuni.myproject.model.entity.enums.AppointmentStatus;
import bg.softuni.myproject.model.entity.enums.TrainingType;
import bg.softuni.myproject.service.AppointmentService;
import bg.softuni.myproject.service.UserService;
import bg.softuni.myproject.service.dto.AddAppointmentDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    public AppointmentController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
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


    @GetMapping("/appointments-all")
    public String getAllAppointments(Model model){

        model.addAttribute("allAppointments", appointmentService.getAllAppointments());

        return "appointments-all";
    }


    @GetMapping("/appointments/{id}")
    public String appointmentDetails(@PathVariable("id") Long id, Model model){

        model.addAttribute("appointmentDetails", appointmentService.getAppointmentDetails(id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long userId = userService.getUserIdByUsername(username);

        boolean isRegistered = appointmentService.isUserRegistered(id, userId);
        model.addAttribute("isRegistered", isRegistered);

        return "details";
    }

    @PostMapping("/appointments/register/{id}")
    public ResponseEntity<?> registerForAppointment(@PathVariable("id") Long id){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long userIdByUsername = userService.getUserIdByUsername(username);


        boolean result = appointmentService.registerForAppointment(id, userIdByUsername);

        if (result){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register.");
        }
    }

    @PostMapping("/appointments/unregister/{id}")
    public ResponseEntity<?> unregisterForAppointment(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long userId = userService.getUserIdByUsername(username);

        boolean result = appointmentService.unregisterFromAppointment(id, userId);

        if (result){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to unregister.");
        }
    }


    @DeleteMapping("/appointments/delete/{id}")
    public String deleteAppointment(@PathVariable("id") Long id){
        appointmentService.deleteAppointment(id);

        return "redirect:/appointments/all";
    }

}