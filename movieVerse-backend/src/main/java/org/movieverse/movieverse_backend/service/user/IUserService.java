package org.movieverse.movieverse_backend.service.user;

import org.movieverse.movieverse_backend.response.LoginResponse;

public interface IUserService {
    LoginResponse login(String username, String password);
    String registerUser(String username, String password);
}
