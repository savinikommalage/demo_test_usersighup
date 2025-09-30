package com.grp264.authdemo.controller;


import com.grp264.authdemo.dto.ApiResponse;
import com.grp264.authdemo.dto.LoginRequest;
import com.grp264.authdemo.dto.SignupRequest;
import com.grp264.authdemo.model.User;
import com.grp264.authdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private static final Logger logger = LoggerFactory.getLogger(AuthRestController.class);

    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody SignupRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
//        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userService.register(user);
        logger.info("User {} registered successfully", request.getUsername());

        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully!"));
    }

    // ✅ Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        var user = userService.login(request.getUsername(), request.getPassword());
        if (user.isPresent()) {
            logger.info("User {} logged in successfully", request.getUsername());
            return ResponseEntity.ok(new ApiResponse(true, "Welcome " + user.get().getUsername() + "!"));
        } else {
            logger.warn("Login failed for user {}", request.getUsername());
            return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid credentials"));
        }
    }
}
