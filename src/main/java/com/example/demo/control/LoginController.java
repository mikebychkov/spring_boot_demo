package com.example.demo.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("showLoginPage")
    public String login() {
        return "login";
    }

    @GetMapping("showAccessDenied")
    public String denied() {
        return "accessDenied";
    }
}
