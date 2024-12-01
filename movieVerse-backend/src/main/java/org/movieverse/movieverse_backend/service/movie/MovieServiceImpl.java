package org.movieverse.movieverse_backend.service.movie;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.exception.ResourceNotFoundException;
import org.movieverse.movieverse_backend.entity.Movie;
import org.movieverse.movieverse_backend.mapper.MovieMapper;
import org.movieverse.movieverse_backend.repository.MovieRepository;
import org.movieverse.movieverse_backend.response.MovieResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        return MovieMapper.toResponseList(movies);
    }

    @Override
    public MovieResponse getMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with ID " + movieId + " not found!"));

        return MovieMapper.toResponse(movie);
    }

    @Override
    public List<MovieResponse> getSuggestedMovies(Long movieId) {
        MovieResponse presentMovie = getMovieById(movieId);
        List<MovieResponse> movieListWithSameType = getMoviesByType(presentMovie.getType());

        List<MovieResponse> movieListWithSameTypeExceptPresentMovie = movieListWithSameType.stream()
                .filter(movie -> !movie.getName().equalsIgnoreCase(presentMovie.getName())).toList();

        int numberOfMoviesToReturn = Math.min(4, movieListWithSameTypeExceptPresentMovie.size());

        return movieListWithSameTypeExceptPresentMovie.subList(0, numberOfMoviesToReturn);
    }


    @Override
    public List<MovieResponse> getMoviesByType(String movieType) {
        List<Movie> movies = movieRepository.findByType(movieType);

        return MovieMapper.toResponseList(movies);
    }

    @Override
    public List<MovieResponse> getPopularMovies() {
        List<Movie> movies = movieRepository.findPopularMovies();

        return MovieMapper.toResponseList(movies);
    }
}
