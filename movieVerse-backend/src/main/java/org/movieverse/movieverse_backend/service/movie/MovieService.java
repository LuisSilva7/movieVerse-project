package org.movieverse.movieverse_backend.service.movie;

import org.movieverse.movieverse_backend.response.MovieResponse;

import java.util.List;

public interface MovieService {
    List<MovieResponse> getAllMovies();
    MovieResponse getMovieById(Long movieId);
    List<MovieResponse> getSuggestedMovies(Long movieId);
    List<MovieResponse> getMoviesByType(String movieType);
    List<MovieResponse> getPopularMovies();
}
