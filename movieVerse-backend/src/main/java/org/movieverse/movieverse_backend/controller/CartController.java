package org.movieverse.movieverse_backend.controller;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.dto.MovieDto;
import org.movieverse.movieverse_backend.entity.Movie;
import org.movieverse.movieverse_backend.response.ApiResponsee;
import org.movieverse.movieverse_backend.service.cart.CartServiceImpl;
import org.movieverse.movieverse_backend.service.movie.MovieServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cart")
@CrossOrigin
public class CartController {

    private final CartServiceImpl cartServiceImpl;
    private final MovieServiceImpl movieServiceImpl;

    @GetMapping("/get-movies")
    public ResponseEntity<ApiResponsee> getAllCartMovies(JwtAuthenticationToken token) {
        List<Movie> movies = cartServiceImpl.getAllCartMovies(token);
        //List<MovieDto> convertedMovies = movieServiceImpl.getConvertedMovies(movies);
        List<MovieDto> convertedMovies = null;
        return ResponseEntity.ok(new ApiResponsee("Success!", convertedMovies));
    }

    @GetMapping("/totalAmount")
    public ResponseEntity<ApiResponsee> getCartTotalAmount(JwtAuthenticationToken token) {
        BigDecimal totalAmount = cartServiceImpl.getCartTotalAmount(token);
        return ResponseEntity.ok(new ApiResponsee("Success!", totalAmount));
    }

    @PostMapping("/add-movie/{movieId}")
    public ResponseEntity<ApiResponsee> addMovie(@PathVariable Long movieId, JwtAuthenticationToken token) {
        String response = cartServiceImpl.addMovie(movieId, token);

        return ResponseEntity.ok(new ApiResponsee(response, null));
    }

    @DeleteMapping("/remove-movie/{movieId}")
    public ResponseEntity<ApiResponsee> removeMovie(@PathVariable Long movieId, JwtAuthenticationToken token) {

        String response = cartServiceImpl.removeMovie(movieId, token);

        return ResponseEntity.ok(new ApiResponsee(response, null));
    }
}
