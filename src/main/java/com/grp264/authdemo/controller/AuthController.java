package com.grp264.authdemo.controller;

import com.grp264.authdemo.model.User;
import com.grp264.authdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

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
            System.out.println("Welcome " + user.get().getUsername() + "!");
            return "home";
        }
        model.addAttribute("error", "Invalid credentials");
        System.out.println("Invalid credentials!");
        return "login";
    }



}
