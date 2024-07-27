package bg.softuni.myproject.controller;

import bg.softuni.myproject.service.UserService;
import bg.softuni.myproject.service.session.FitnessUserDetailsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }


//    @GetMapping("/")
//    public String home(@AuthenticationPrincipal UserDetails userDetails,
//                       Model model){
//       return "";
//
//    }

    @GetMapping("/about")
    public String viewAbout(){
        return "about";
    }

    @GetMapping("/profile")
    public String viewProfile(Model model){
        model.addAttribute("profileData", userService.findUser());
        return "profile";
    }

//    @GetMapping("/details")
//    public String viewDetails(){
//        return "details";
//    }
}
