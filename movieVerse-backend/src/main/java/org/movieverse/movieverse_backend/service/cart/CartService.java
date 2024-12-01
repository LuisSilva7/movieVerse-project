package org.movieverse.movieverse_backend.service.cart;

import com.stripe.exception.StripeException;
import org.movieverse.movieverse_backend.entity.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    List<Movie> getAllCartMovies(JwtAuthenticationToken token);
    BigDecimal getCartTotalAmount(JwtAuthenticationToken token);
    String addMovie(Long movieId, JwtAuthenticationToken token);
    String removeMovie(Long movieId, JwtAuthenticationToken token);
    ResponseEntity<String> createCheckoutSession(JwtAuthenticationToken token) throws StripeException;
}
