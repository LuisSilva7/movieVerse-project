package org.movieverse.movieverse_backend.cart;

import java.math.BigDecimal;

public record CartResponse(
        Long id,
        String image,
        String name,
        BigDecimal price,
        double rating
) {
}
