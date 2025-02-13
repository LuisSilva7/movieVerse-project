package org.movieverse.movieverse_backend.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    public StripeConfig(@Value("${application.stripe.api.key}") String apiKey) {
        Stripe.apiKey = apiKey;
    }
}
