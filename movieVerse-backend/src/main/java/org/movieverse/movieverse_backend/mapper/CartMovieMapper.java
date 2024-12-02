package org.movieverse.movieverse_backend.mapper;

import org.movieverse.movieverse_backend.entity.Movie;
import org.movieverse.movieverse_backend.response.CartMovieResponse;

import java.util.List;

public class CartMovieMapper {
    public static CartMovieResponse toResponse(Movie movie) {
        return new CartMovieResponse(
                movie.getId(),
                movie.getImage(),
                movie.getName(),
                movie.getPrice(),
                movie.getRating()
        );
    }

    public static List<CartMovieResponse> toResponseList(List<Movie> movies) {
        return movies.stream()
                .map(CartMovieMapper::toResponse)
                .toList();
    }
}
