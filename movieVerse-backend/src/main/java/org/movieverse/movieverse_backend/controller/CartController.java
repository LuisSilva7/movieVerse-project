package org.movieverse.movieverse_backend.controller;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.dto.MovieDto;
import org.movieverse.movieverse_backend.model.Movie;
import org.movieverse.movieverse_backend.response.ApiResponse;
import org.movieverse.movieverse_backend.service.cart.CartService;
import org.movieverse.movieverse_backend.service.movie.MovieService;
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

    private final CartService cartService;
    private final MovieService movieService;

    @GetMapping("/get-movies")
    public ResponseEntity<ApiResponse> getAllCartMovies(JwtAuthenticationToken token) {
        List<Movie> movies = cartService.getAllCartMovies(token);
        List<MovieDto> convertedMovies = movieService.getConvertedMovies(movies);
        return ResponseEntity.ok(new ApiResponse("Success!", convertedMovies));
    }

    @GetMapping("/totalAmount")
    public ResponseEntity<ApiResponse> getCartTotalAmount(JwtAuthenticationToken token) {
        BigDecimal totalAmount = cartService.getCartTotalAmount(token);
        return ResponseEntity.ok(new ApiResponse("Success!", totalAmount));
    }

    @PostMapping("/add-movie/{movieId}")
    public ResponseEntity<ApiResponse> addMovie(@PathVariable Long movieId, JwtAuthenticationToken token) {
        String response = cartService.addMovie(movieId, token);

        return ResponseEntity.ok(new ApiResponse(response, null));
    }

    @DeleteMapping("/remove-movie/{movieId}")
    public ResponseEntity<ApiResponse> removeMovie(@PathVariable Long movieId, JwtAuthenticationToken token) {

        String response = cartService.removeMovie(movieId, token);

        return ResponseEntity.ok(new ApiResponse(response, null));
    }
}
