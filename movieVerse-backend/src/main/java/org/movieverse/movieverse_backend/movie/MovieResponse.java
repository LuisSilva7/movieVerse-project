package org.movieverse.movieverse_backend.movie;

import java.math.BigDecimal;

public record MovieResponse(
        Long id,
        String image,
        String imageVertical,
        String name,
        String description,
        String type,
        BigDecimal price,
        int duration,
        double rating
) {
}
