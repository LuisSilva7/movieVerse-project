package org.movieverse.movieverse_backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.request.RegisterUserRequest;
import org.movieverse.movieverse_backend.request.LoginRequest;
import org.movieverse.movieverse_backend.response.*;
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

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterUserResponse>> register(
            @Valid @RequestBody RegisterUserRequest registerUserRequest) {
        RegisterUserResponse registerUserResponse = userService
                .register(registerUserRequest.getUsername(), registerUserRequest.getPassword());

        return ResponseEntity.ok(new ApiResponse<>(
                "Success",
                "User with ID " + registerUserResponse.getId() + " registered successfully!",
                null));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = userService
                    .login(loginRequest.getUsername(), loginRequest.getPassword());

            return ResponseEntity.ok(loginResponse);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
