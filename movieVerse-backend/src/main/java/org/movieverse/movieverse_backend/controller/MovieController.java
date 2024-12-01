package org.movieverse.movieverse_backend.controller;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.response.ApiResponse;
import org.movieverse.movieverse_backend.response.MovieResponse;
import org.movieverse.movieverse_backend.service.movie.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/movies")
@CrossOrigin
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> getAllMovies() {
        List<MovieResponse> movieResponseList = movieService.getAllMovies();

        return ResponseEntity.ok(new ApiResponse<>(
                "Success", "Movies obtained successfully!", movieResponseList));
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<ApiResponse<MovieResponse>> getMovieById(@PathVariable Long movieId) {
        MovieResponse movieResponse = movieService.getMovieById(movieId);

        return ResponseEntity.ok(new ApiResponse<>(
                "Success", "Movie with ID " + movieId + " obtained successfully!", movieResponse));
    }

    @GetMapping("/{movieId}/suggestions")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> getSuggestedMovies(@PathVariable Long movieId) {
        List<MovieResponse> movieResponseList = movieService.getSuggestedMovies(movieId);

        return ResponseEntity.ok(new ApiResponse<>(
                "Success", "Suggested Movies obtained successfully!", movieResponseList));
    }

    @GetMapping("/type/{movieType}")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> getMoviesByType(@PathVariable String movieType) {
        List<MovieResponse> movieResponseList = movieService.getMoviesByType(movieType);

        return ResponseEntity.ok(new ApiResponse<>(
                "Success", "Movies (" + movieType + ") obtained successfully!", movieResponseList));
    }

    @GetMapping("/popular")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> getPopularMovies() {
        List<MovieResponse> movieResponseList = movieService.getPopularMovies();

        return ResponseEntity.ok(new ApiResponse<>(
                "Success", "Popular movies obtained successfully!", movieResponseList));
    }
}
