package bg.softuni.myproject.controller;

import bg.softuni.myproject.service.session.FitnessUserDetailsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {





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
    public String viewProfile(){
        return "profile";
    }

//    @GetMapping("/details")
//    public String viewDetails(){
//        return "details";
//    }
}
