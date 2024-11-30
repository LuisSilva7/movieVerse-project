package org.movieverse.movieverse_backend.service.cart;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.model.Cart;
import org.movieverse.movieverse_backend.model.Movie;
import org.movieverse.movieverse_backend.model.User;
import org.movieverse.movieverse_backend.repository.CartRepository;
import org.movieverse.movieverse_backend.repository.UserRepository;
import org.movieverse.movieverse_backend.service.movie.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.*;

@RequiredArgsConstructor
@Service
public class CartService implements ICartService{

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final MovieService movieService;

    @Override
    public List<Movie> getAllCartMovies(JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName()));

        if(user.isPresent()) {
            Cart cart = user.get().getCart();
            return cart.getMovies();
        }

        return List.of();
    }

    @Override
    public BigDecimal getCartTotalAmount(JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName()));

        if(user.isPresent()) {
            Cart cart = user.get().getCart();
            return cart.getTotalAmount();
        }

        return null;
    }

    @Override
    public String addMovie(Long movieId, JwtAuthenticationToken token) {
        Movie movieFromDb = movieService.getMovieById(movieId);

        var user = userRepository.findById(UUID.fromString(token.getName()));

        if(user.isPresent()) {
            Cart cart = user.get().getCart();
            cart.getMovies().forEach(movie -> {
                if(movie.getId().equals(movieFromDb.getId())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie already in cart");
                }
            });
            cart.addMovie(movieFromDb);
            cartRepository.save(cart);
        }

        return "Movie added to cart successfully!";
    }

    @Override
    public String removeMovie(Long movieId, JwtAuthenticationToken token) {
        Movie movieFromDb = movieService.getMovieById(movieId);

        var user = userRepository.findById(UUID.fromString(token.getName()));

        if(user.isPresent()) {
            Cart cart = user.get().getCart();

            for(Movie movie : cart.getMovies()) {
                if(Objects.equals(movie.getId(), movieFromDb.getId())) {
                    cart.removeMovie(movieFromDb);
                    cartRepository.save(cart);

                    return "Movie removed from cart successfully!";
                }
            }
        }

        return "Movie was not removed from cart!";
    }

    @Override
    public ResponseEntity<String> createCheckoutSession(JwtAuthenticationToken token) throws StripeException {

        Optional<User> user = userRepository.findById(UUID.fromString(token.getName()));

        if (user.isPresent()) {
            Cart cart = user.get().getCart();

            SessionCreateParams.Builder sessionBuilder = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:5173/")
                    .setCancelUrl("http://localhost:5173/error");

            for (Movie movie : cart.getMovies()) {
                SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                        .setPrice(movie.getStripeId())
                        .setQuantity(1L)
                        .build();
                sessionBuilder.addLineItem(lineItem);
            }

            Session session = Session.create(sessionBuilder.build());

            cart.removeMovies();
            cartRepository.save(cart);

            return ResponseEntity.ok(Map.of("url", session.getUrl()).toString());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Something went wrong with the payment!").toString());
    }

}
