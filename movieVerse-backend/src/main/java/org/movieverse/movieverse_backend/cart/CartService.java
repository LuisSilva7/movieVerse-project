package org.movieverse.movieverse_backend.cart;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.exception.custom.ResourceAlreadyExistsException;
import org.movieverse.movieverse_backend.exception.custom.ResourceNotFoundException;
import org.movieverse.movieverse_backend.movie.Movie;
import org.movieverse.movieverse_backend.movie.MovieService;
import org.movieverse.movieverse_backend.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final MovieService movieService;

    public List<CartResponse> findAllCartMovies(Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());

        Cart cart = user.getCart();
        if(cart == null || cart.getMovies().isEmpty()) {
            throw new ResourceNotFoundException("User with ID: " + user.getId() + " has no movies in cart!");
        }

        return cart.getMovies().stream()
                .map(cartMapper::toCartResponse)
                .toList();
    }

    public BigDecimal findCartTotalAmount(Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());

        Cart cart = user.getCart();
        if(cart == null || cart.getMovies().isEmpty()) {
            return BigDecimal.ZERO;
        }

        return cart.getTotalAmount();
    }

    public void addCartMovie(Long movieId, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());

        Movie movie = movieService.findById(movieId);

        Cart cart = user.getCart();

        if (cart.getMovies().stream()
                .anyMatch(m -> m.getId().equals(movieId))) {
            throw new ResourceAlreadyExistsException("Movie with ID " + movieId + " already in cart!");
        }

        cart.addMovie(movie);
        cartRepository.save(cart);
    }

    public void deleteCartMovie(Long movieId, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());

        Movie movie = movieService.findById(movieId);

        Cart cart = user.getCart();

        if(cart.getMovies().stream().noneMatch(m -> m.getId().equals(movieId))) {
            throw new ResourceNotFoundException("Movie with ID: " + movie.getId() + " not found in cart!");
        }

        cart.removeMovie(movie);
        cartRepository.save(cart);
    }


    public String createCheckoutSession(Authentication connectedUser) throws StripeException {
        User user = ((User) connectedUser.getPrincipal());

        Cart cart = user.getCart();

        SessionCreateParams.Builder sessionBuilder = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:5000/")
                .setCancelUrl("http://localhost:5000/error");

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

        return session.getUrl();
    }

}
