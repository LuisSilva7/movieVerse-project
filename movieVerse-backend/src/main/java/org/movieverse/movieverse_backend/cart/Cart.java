package org.movieverse.movieverse_backend.cart;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.movieverse.movieverse_backend.common.BaseEntity;
import org.movieverse.movieverse_backend.movie.Movie;
import org.movieverse.movieverse_backend.user.User;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Cart extends BaseEntity {

    private BigDecimal totalAmount;

    @ManyToMany
    @JoinTable(name="cart_movie",
            joinColumns=
            @JoinColumn(name="cart_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="movie_id", referencedColumnName="id")
    )
    private List<Movie> movies;

    @OneToOne(mappedBy = "cart")
    private User user;

    public void addMovie(Movie movie) {
        this.movies.add(movie);
        updateTotalAmount();
    }

    public void removeMovie(Movie movie) {
        this.movies.remove(movie);
        updateTotalAmount();
    }

    public void removeMovies() {
        this.movies.clear();
        updateTotalAmount();
    }

    private void updateTotalAmount() {
        this.totalAmount = movies.stream().map(movie -> {
            BigDecimal moviePrice = movie.getPrice();
            return Objects.requireNonNullElse(moviePrice, BigDecimal.ZERO);
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}