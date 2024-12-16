package org.movieverse.movieverse_backend.movie;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.common.ApiResponse;
import org.movieverse.movieverse_backend.common.PageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<PageResponse<MovieResponse>>> findAllMovies(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        PageResponse<MovieResponse> response = movieService.findAllMovies(page, size);

        return ResponseEntity.ok(new ApiResponse<>(
                "Movies obtained successfully!", response));
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<ApiResponse<MovieResponse>> findMovieById(@PathVariable Long movieId) {
        MovieResponse response = movieService.findMovieById(movieId);

        return ResponseEntity.ok(new ApiResponse<>(
                "Movie with ID: " + movieId + " obtained successfully!", response));
    }

    @GetMapping("/{movieId}/suggestions")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> findSuggestedMovies(@PathVariable Long movieId) {
        List<MovieResponse> response = movieService.findSuggestedMovies(movieId);

        return ResponseEntity.ok(new ApiResponse<>(
                "Suggested Movies obtained successfully!", response));
    }

    @GetMapping("/type/{movieType}")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> findMoviesByType(@PathVariable String movieType) {
        List<MovieResponse> response = movieService.findMoviesByType(movieType);

        return ResponseEntity.ok(new ApiResponse<>(
                "Movies (" + movieType + ") obtained successfully!", response));
    }

    @GetMapping("/popular")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> findPopularMovies() {
        List<MovieResponse> response = movieService.findPopularMovies();

        return ResponseEntity.ok(new ApiResponse<>(
                "Popular movies obtained successfully!", response));
    }
}
