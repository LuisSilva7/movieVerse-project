package org.movieverse.movieverse_backend.entity;

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
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "image_vertical", nullable = false)
    private String imageVertical;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "type", nullable = false)
    private String type; // Ex: Comedy, Action, etc.

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "duration", nullable = false)
    private int duration; // In minutes

    @Column(name = "rating", nullable = false)
    private double rating; // Ex: 7.8

    @Column(name = "stripe_id", nullable = false)
    private String stripeId;

    @ManyToMany(mappedBy="movies")
    private List<Cart> carts;
}
