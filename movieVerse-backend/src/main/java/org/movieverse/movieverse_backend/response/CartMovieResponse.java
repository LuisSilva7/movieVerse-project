package org.movieverse.movieverse_backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class CartMovieResponse {
    private Long id;
    private String image;
    private String name;
    private BigDecimal price;
    private double rating;
}