package com.vetsportvzla.backend.controller;

import com.vetsportvzla.backend.dto.LoginRequest;
import com.vetsportvzla.backend.dto.UserLoginResponse;
import com.vetsportvzla.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody LoginRequest request) {
        UserLoginResponse response = userService.loginUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(response);
    }
}
