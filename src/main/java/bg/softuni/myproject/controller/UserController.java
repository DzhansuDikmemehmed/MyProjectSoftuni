package bg.softuni.myproject.controller;


import bg.softuni.myproject.model.entity.enums.Level;
import bg.softuni.myproject.service.UserService;
import bg.softuni.myproject.service.dto.UserLoginDto;
import bg.softuni.myproject.service.dto.UserRegistrationDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registrationData")
    public UserRegistrationDto registrationDto(){
        return new UserRegistrationDto();
    }

    @ModelAttribute("loginData")
    public UserLoginDto loginDto(){
        return new UserLoginDto();
    }



    @GetMapping("/register")
    public String viewRegister(Model model){
        model.addAttribute("level", Level.values());

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDto userRegistrationDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
      if (bindingResult.hasErrors()){
          redirectAttributes.addFlashAttribute("registrationData",userRegistrationDto);
          redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationData", bindingResult);

          return "redirect:/users/register";
      }

        userService.registerUser(userRegistrationDto);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String viewLogin(){
        return "login";
    }

    @GetMapping("/login-error")
    public String viewLoginError(Model model){
        model.addAttribute("showErrorMessage", true);


        return "login";
    }


}
