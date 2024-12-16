package org.movieverse.movieverse_backend.cart;

import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/carts")
@CrossOrigin
public class CartController {

    private final CartService cartService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<CartResponse>>> findAllCartMovies(Authentication connectedUser) {
        List<CartResponse> response = cartService.findAllCartMovies(connectedUser);

        return ResponseEntity.ok(new ApiResponse<>(
                "Cart movies obtained successfully!", response));
    }

    @GetMapping("/totalAmount")
    public ResponseEntity<ApiResponse<BigDecimal>> getCartTotalAmount(Authentication connectedUser) {
        BigDecimal totalAmount = cartService.findCartTotalAmount(connectedUser);

        return ResponseEntity.ok(new ApiResponse<>(
                "Cart total amount obtained successfully!", totalAmount));
    }

    @PostMapping("/{movieId}")
    public ResponseEntity<ApiResponse<?>> addCartMovie(
            @PathVariable Long movieId, Authentication connectedUser) {
        cartService.addCartMovie(movieId, connectedUser);

        return ResponseEntity.ok(new ApiResponse<>(
                "Movie with ID " + movieId + " added successfully!",
                null));
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<ApiResponse<?>> deleteCartMovie(
            @PathVariable Long movieId, Authentication connectedUser) {
        cartService.deleteCartMovie(movieId, connectedUser);

        return ResponseEntity.ok(new ApiResponse<>(
                "Movie with ID " + movieId + " deleted successfully!",
                null));
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<ApiResponse<String>> createCheckoutSession(Authentication connectedUser)
            throws StripeException {
        String urlSession = cartService.createCheckoutSession(connectedUser);

        return ResponseEntity.ok(new ApiResponse<>(
                "Created checkout session successfully!",
                urlSession));
    }
}
