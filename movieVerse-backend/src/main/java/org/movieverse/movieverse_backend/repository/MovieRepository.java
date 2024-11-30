package org.movieverse.movieverse_backend.repository;

import org.movieverse.movieverse_backend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByType(String type);

    // Get the 12 first movies
    @Query(value = "SELECT * FROM movie LIMIT 12", nativeQuery = true)
    List<Movie> findPopularMovies();
}
