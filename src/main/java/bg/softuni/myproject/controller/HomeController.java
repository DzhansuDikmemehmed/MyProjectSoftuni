package bg.softuni.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String viewLogin(){
        return "login";
    }




    @GetMapping("/about")
    public String viewAbout(){
        return "about";
    }

    @GetMapping("/profile")
    public String viewProfile(){
        return "profile";
    }
}
