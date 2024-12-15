package org.movieverse.movieverse_backend.movie;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.movieverse.movieverse_backend.cart.Cart;
import org.movieverse.movieverse_backend.common.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Movie extends BaseEntity {

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
