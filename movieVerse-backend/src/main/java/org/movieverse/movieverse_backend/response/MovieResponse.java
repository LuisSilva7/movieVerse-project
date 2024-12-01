package org.movieverse.movieverse_backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class MovieResponse {
    private Long id;
    private String image;
    private String imageVertical;
    private String name;
    private String description;
    private String type;
    private BigDecimal price;
    private int duration;
    private double rating;
}
