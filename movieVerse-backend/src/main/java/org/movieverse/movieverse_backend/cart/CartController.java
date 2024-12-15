package org.movieverse.movieverse_backend.cart;

import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin
public class CartController {

    private final CartService cartService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<CartResponse>>> findAllCartMovies(JwtAuthenticationToken token) {
        List<CartResponse> response = cartService.findAllCartMovies(token);

        return ResponseEntity.ok(new ApiResponse<>(
                "Cart movies obtained successfully!", response));
    }

    @GetMapping("/totalAmount")
    public ResponseEntity<ApiResponse<BigDecimal>> getCartTotalAmount(JwtAuthenticationToken token) {
        BigDecimal totalAmount = cartService.findCartTotalAmount(token);

        return ResponseEntity.ok(new ApiResponse<>(
                "Cart total amount obtained successfully!", totalAmount));
    }

    @PostMapping("/{movieId}")
    public ResponseEntity<ApiResponse<?>> addCartMovie(
            @PathVariable Long movieId, JwtAuthenticationToken token) {
        cartService.addCartMovie(movieId, token);

        return ResponseEntity.ok(new ApiResponse<>(
                "Movie with ID " + movieId + " added successfully!",
                null));
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<ApiResponse<?>> deleteCartMovie(
            @PathVariable Long movieId, JwtAuthenticationToken token) {
        cartService.deleteCartMovie(movieId, token);

        return ResponseEntity.ok(new ApiResponse<>(
                "Movie with ID " + movieId + " deleted successfully!",
                null));
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<ApiResponse<String>> createCheckoutSession(JwtAuthenticationToken token)
            throws StripeException {
        String urlSession = cartService.createCheckoutSession(token);

        return ResponseEntity.ok(new ApiResponse<>(
                "Created checkout session successfully!",
                urlSession));
    }
}
