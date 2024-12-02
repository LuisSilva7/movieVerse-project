package org.movieverse.movieverse_backend.mapper;

import org.movieverse.movieverse_backend.entity.Movie;
import org.movieverse.movieverse_backend.response.MovieResponse;

import java.util.List;

public class MovieMapper {

    public static MovieResponse toResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getImage(),
                movie.getImageVertical(),
                movie.getName(),
                movie.getDescription(),
                movie.getType(),
                movie.getPrice(),
                movie.getDuration(),
                movie.getRating()
        );
    }

    public static List<MovieResponse> toResponseList(List<Movie> movies) {
        return movies.stream()
                .map(MovieMapper::toResponse)
                .toList();
    }
}
