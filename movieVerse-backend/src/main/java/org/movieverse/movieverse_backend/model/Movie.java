package org.movieverse.movieverse_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String imageVertical;
    private String name;
    private String description;
    private String type; // Ex: Comedy, Action, etc.
    private BigDecimal price;
    private int duration; // In minutes
    private double rating; // Ex: 7.8
    private String stripeId;

    @ManyToMany(mappedBy="movies")
    private List<Cart> carts;
}
