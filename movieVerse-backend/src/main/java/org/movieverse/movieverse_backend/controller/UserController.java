package org.movieverse.movieverse_backend.controller;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.request.RegisterUserRequest;
import org.movieverse.movieverse_backend.request.LoginRequest;
import org.movieverse.movieverse_backend.response.ApiResponse;
import org.movieverse.movieverse_backend.response.LoginResponse;
import org.movieverse.movieverse_backend.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/auth")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = userService
                    .login(loginRequest.getUsername(), loginRequest.getPassword());

            return ResponseEntity.ok(loginResponse);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/registerUser")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {

        String response = userService
                .registerUser(registerUserRequest.getUsername(), registerUserRequest.getPassword());

        if(response.equals("User registered successfully!")) {
            return ResponseEntity.ok(new ApiResponse(response, null));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
