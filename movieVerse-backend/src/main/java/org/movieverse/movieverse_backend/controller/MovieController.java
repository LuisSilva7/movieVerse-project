package org.movieverse.movieverse_backend.controller;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.dto.MovieDto;
import org.movieverse.movieverse_backend.exceptions.ResourceNotFoundException;
import org.movieverse.movieverse_backend.model.Movie;
import org.movieverse.movieverse_backend.response.ApiResponse;
import org.movieverse.movieverse_backend.service.movie.IMovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/movies")
@CrossOrigin
public class MovieController {

    private final IMovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        List<MovieDto> convertedMovies = movieService.getConvertedMovies(movies);
        return ResponseEntity.ok(new ApiResponse("Success!", convertedMovies));
    }

    @GetMapping("/{movieType}")
    public ResponseEntity<ApiResponse> getMoviesByType(@PathVariable String movieType){
        try {
            List<Movie> movies = movieService.getMoviesByType(movieType);
            if (movies.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No movies were found!", null));
            }
            List<MovieDto> convertedMovies = movieService.getConvertedMovies(movies);
            return  ResponseEntity.ok(new ApiResponse("Success!", convertedMovies));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", e.getMessage()));
        }
    }

    @GetMapping("/popular")
    public ResponseEntity<ApiResponse> getPopularMovies() {
        List<Movie> movies = movieService.getPopularMovies();
        List<MovieDto> convertedMovies = movieService.getConvertedMovies(movies);
        return ResponseEntity.ok(new ApiResponse("Success!", convertedMovies));
    }

    @GetMapping("/suggestion/{movieId}")
    public ResponseEntity<ApiResponse> getSuggestionMovies(@PathVariable Long movieId) {
        List<Movie> movies = movieService.getSuggetionMovies(movieId);
        List<MovieDto> convertedMovies = movieService.getConvertedMovies(movies);
        return ResponseEntity.ok(new ApiResponse("Success!", convertedMovies));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<ApiResponse> getMovieById(@PathVariable Long movieId) {
        try {
            Movie movie = movieService.getMovieById(movieId);
            MovieDto convertedMovie = movieService.getConvertedMovie(movie);
            return  ResponseEntity.ok(new ApiResponse("Success!", convertedMovie));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
