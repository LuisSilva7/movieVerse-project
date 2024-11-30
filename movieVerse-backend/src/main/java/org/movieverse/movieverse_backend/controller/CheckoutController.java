package org.movieverse.movieverse_backend.controller;

import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.service.cart.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/checkout")
@CrossOrigin
public class CheckoutController {

    private final CartService cartService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<String> createCheckoutSession(JwtAuthenticationToken token) {
        try {
            return cartService.createCheckoutSession(token);
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to create Stripe checkout session.").toString());
        }
    }

}
