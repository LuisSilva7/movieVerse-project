package org.movieverse.movieverse_backend.config.data;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.movie.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Configuration
public class MoviesStarter implements CommandLineRunner {

    private final MovieService movieService;

    @Override
    @Transactional
    public void run(String... args) {
        movieService.moviesStarter();
    }
}
