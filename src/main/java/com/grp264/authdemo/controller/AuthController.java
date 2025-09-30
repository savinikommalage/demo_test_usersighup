package com.grp264.authdemo.controller;

import com.grp264.authdemo.model.User;
import com.grp264.authdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "login"; // redirect root to login.html
    }


    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, Model model) {
        userService.register(user);
        model.addAttribute("message", "User registered successfully!");
        logger.info("User {} registered successfully", user.getUsername());
        return "login"; // redirect to login page
    }

    @GetMapping("/getlogin")
    public String loginForm() {
        return "login"; // loads login.html
    }

    @PostMapping("/dologin")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        var user = userService.login(username, password);
        if (user.isPresent()) {
            model.addAttribute("message", "Welcome " + user.get().getUsername() + "!");
            logger.info("User {} logged in successfully", user.get().getUsername());
            return "home";
        }
        model.addAttribute("error", "Invalid credentials");
        logger.info("User {} logged in successfully", user.get().getUsername());
        return "login";
    }



}
