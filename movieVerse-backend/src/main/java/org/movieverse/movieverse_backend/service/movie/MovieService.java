package org.movieverse.movieverse_backend.service.movie;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.dto.MovieDto;
import org.movieverse.movieverse_backend.exceptions.ResourceNotFoundException;
import org.movieverse.movieverse_backend.model.Movie;
import org.movieverse.movieverse_backend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService{

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesByType(String type) {
        return movieRepository.findByType(type);
    }

    @Override
    public List<Movie> getPopularMovies() {
        return movieRepository.findPopularMovies();
    }

    @Override
    public List<Movie> getSuggetionMovies(Long movieId) {
        Movie presentMovie = getMovieById(movieId);
        List<Movie> moviesType = getMoviesByType(presentMovie.getType());

        moviesType.removeIf(movie -> movie.getName().equalsIgnoreCase(presentMovie.getName()));

        int numberOfMoviesToGet = Math.min(4, moviesType.size());
        return moviesType.subList(0, numberOfMoviesToGet);
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found!"));
    }

    @Override
    public MovieDto getConvertedMovie(Movie movie) {
        return new MovieDto(
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

    @Override
    public List<MovieDto> getConvertedMovies(List<Movie> movies) {
        List<MovieDto> movieDtos = new ArrayList<>();
        for(Movie movie : movies) {
            MovieDto movieDto = getConvertedMovie(movie);
            movieDtos.add(movieDto);
        }

        return movieDtos;
    }
}
