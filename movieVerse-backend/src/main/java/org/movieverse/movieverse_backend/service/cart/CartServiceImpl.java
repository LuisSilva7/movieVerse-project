package org.movieverse.movieverse_backend.service.cart;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.entity.Cart;
import org.movieverse.movieverse_backend.entity.Movie;
import org.movieverse.movieverse_backend.entity.User;
import org.movieverse.movieverse_backend.exception.ResourceAlreadyExistsException;
import org.movieverse.movieverse_backend.exception.ResourceNotFoundException;
import org.movieverse.movieverse_backend.mapper.CartMovieMapper;
import org.movieverse.movieverse_backend.repository.CartRepository;
import org.movieverse.movieverse_backend.repository.MovieRepository;
import org.movieverse.movieverse_backend.repository.UserRepository;
import org.movieverse.movieverse_backend.response.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Override
    public List<CartMovieResponse> getAllCartMovies(JwtAuthenticationToken token) {
        Long userId = Long.parseLong(token.getName());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        Cart cart = user.getCart();
        if(cart == null || cart.getMovies().isEmpty()) {
            throw new ResourceNotFoundException("Cart has no movies!");
        }

        return CartMovieMapper.toResponseList(cart.getMovies());
    }


    @Override
    public CartTotalAmountResponse getCartTotalAmount(JwtAuthenticationToken token) {
        Long userId = Long.parseLong(token.getName());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        Cart cart = user.getCart();
        if(cart == null || cart.getMovies().isEmpty()) {
            return new CartTotalAmountResponse(BigDecimal.ZERO);
        }

        return new CartTotalAmountResponse(cart.getTotalAmount());
    }

    @Override
    public RegisterCartMovieResponse registerCartMovie(Long movieId, JwtAuthenticationToken token) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with ID " + movieId + " not found!"));

        Long userId = Long.parseLong(token.getName());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        Cart cart = user.getCart();

        if (cart.getMovies().stream()
                .anyMatch(m -> m.getId().equals(movieId))) {
            throw new ResourceAlreadyExistsException("Movie with ID " + movieId + " already in cart!");
        }

        cart.addMovie(movie);
        cartRepository.save(cart);

        return new RegisterCartMovieResponse(movieId);
    }

    @Override
    public DeleteCartMovieResponse deleteCartMovie(Long movieId, JwtAuthenticationToken token) {
        movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with ID " + movieId + " not found!"));

        Long userId = Long.parseLong(token.getName());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        Cart cart = user.getCart();
        boolean cartMovieRemoved = cart.getMovies().removeIf(m -> m.getId().equals(movieId));
        if(!cartMovieRemoved) {
            throw new ResourceNotFoundException("Cart movie with ID " + movieId + " not found!");
        }

        cartRepository.save(cart);

        return new DeleteCartMovieResponse(movieId);
    }


    @Override
    public CreateCheckoutSessionResponse createCheckoutSession(JwtAuthenticationToken token) throws StripeException {
        Long userId = Long.parseLong(token.getName());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        Cart cart = user.getCart();

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

        // RETIRAR O FILME APENAS QUANDO FOR PAGO NO CHECKOUT
        cart.removeMovies();
        cartRepository.save(cart);

        return new CreateCheckoutSessionResponse(session.getUrl());
    }

}
