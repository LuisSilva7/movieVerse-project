package org.movieverse.movieverse_backend.service.movie;

import org.movieverse.movieverse_backend.dto.MovieDto;
import org.movieverse.movieverse_backend.model.Movie;

import java.util.List;

public interface IMovieService {
    List<Movie> getAllMovies();
    List<Movie> getMoviesByType(String type);
    List<Movie> getPopularMovies();
    List<Movie> getSuggetionMovies(Long movieId);
    Movie getMovieById(Long id);
    MovieDto getConvertedMovie(Movie movie);
    List<MovieDto> getConvertedMovies(List<Movie> movies);
}
