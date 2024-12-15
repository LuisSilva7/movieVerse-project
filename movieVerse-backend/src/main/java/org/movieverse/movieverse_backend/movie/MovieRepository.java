package org.movieverse.movieverse_backend.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByType(String type);

    // Find the 12 first movies
    @Query(value = "SELECT * FROM movie LIMIT 12", nativeQuery = true)
    List<Movie> findPopularMovies();

    // Find suggested Movies
    @Query(value = "SELECT * FROM movie m WHERE m.type = :movieType AND m.id <> :movieId LIMIT 4", nativeQuery = true)
    List<Movie> findMoviesByTypeExcludingId(@Param("movieType") String movieType, @Param("movieId") Long movieId);
}
