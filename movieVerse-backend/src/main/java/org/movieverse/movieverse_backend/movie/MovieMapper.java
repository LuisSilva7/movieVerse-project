package org.movieverse.movieverse_backend.movie;

import org.springframework.stereotype.Service;

@Service
public class MovieMapper {

    public MovieResponse toMovieResponse(Movie movie) {
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
}