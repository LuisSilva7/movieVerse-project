package org.movieverse.movieverse_backend.cart;

import org.movieverse.movieverse_backend.movie.Movie;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {

    public CartResponse toCartResponse(Movie movie) {
        return new CartResponse(
                movie.getId(),
                movie.getImage(),
                movie.getName(),
                movie.getPrice(),
                movie.getRating()
        );
    }
}