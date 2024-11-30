package org.movieverse.movieverse_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class MovieDto {
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
