package org.movieverse.movieverse_backend.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisterUserRequest {
    private String username;
    private String password;
}
