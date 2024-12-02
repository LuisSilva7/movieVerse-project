package org.movieverse.movieverse_backend.service.cart;

import com.stripe.exception.StripeException;
import org.movieverse.movieverse_backend.response.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public interface CartService {
    List<CartMovieResponse> getAllCartMovies(JwtAuthenticationToken token);
    CartTotalAmountResponse getCartTotalAmount(JwtAuthenticationToken token);
    RegisterCartMovieResponse registerCartMovie(Long movieId, JwtAuthenticationToken token);
    DeleteCartMovieResponse deleteCartMovie(Long movieId, JwtAuthenticationToken token);
    CreateCheckoutSessionResponse createCheckoutSession(JwtAuthenticationToken token) throws StripeException;
}
