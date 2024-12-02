package org.movieverse.movieverse_backend.controller;

import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.response.*;
import org.movieverse.movieverse_backend.service.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cart")
@CrossOrigin
public class CartController {

    private final CartService cartService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<CartMovieResponse>>> getAllCartMovies(JwtAuthenticationToken token) {
        List<CartMovieResponse> cartMovieResponseList = cartService.getAllCartMovies(token);

        return ResponseEntity.ok(new ApiResponse<>(
                "Success", "Cart movies obtained successfully!", cartMovieResponseList));
    }

    @GetMapping("/totalAmount")
    public ResponseEntity<ApiResponse<CartTotalAmountResponse>> getCartTotalAmount(JwtAuthenticationToken token) {
        CartTotalAmountResponse cartTotalAmountResponse = cartService.getCartTotalAmount(token);

        return ResponseEntity.ok(new ApiResponse<>(
                "Success", "Cart total amount obtained successfully!", cartTotalAmountResponse));
    }

    @PostMapping("/{movieId}")
    public ResponseEntity<ApiResponse<RegisterCartMovieResponse>> registerCartMovie(
            @PathVariable Long movieId, JwtAuthenticationToken token) {
        RegisterCartMovieResponse registerCartMovieResponse = cartService.registerCartMovie(movieId, token);

        return ResponseEntity.ok(new ApiResponse<>(
                "Success",
                "Movie with ID " + movieId + " registered successfully!",
                registerCartMovieResponse));
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<ApiResponse<DeleteCartMovieResponse>> deleteCartMovie(
            @PathVariable Long movieId, JwtAuthenticationToken token) {
        DeleteCartMovieResponse deleteCartMovieResponse = cartService.deleteCartMovie(movieId, token);

        return ResponseEntity.ok(new ApiResponse<>(
                "Success",
                "Movie with ID " + movieId + " deleted successfully!",
                deleteCartMovieResponse));
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<ApiResponse<CreateCheckoutSessionResponse>> createCheckoutSession(JwtAuthenticationToken token) throws StripeException {
        CreateCheckoutSessionResponse createCheckoutSessionResponse = cartService.createCheckoutSession(token);

        return ResponseEntity.ok(new ApiResponse<>(
                "Success",
                "Created checkout session successfully!",
                createCheckoutSessionResponse));
    }
}
