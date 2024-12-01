package org.movieverse.movieverse_backend.service.user;

import org.movieverse.movieverse_backend.response.LoginResponse;
import org.movieverse.movieverse_backend.response.RegisterUserResponse;

public interface UserService {
    RegisterUserResponse register(String username, String password);
    LoginResponse login(String username, String password);
}
