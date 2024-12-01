package org.movieverse.movieverse_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "total_amount")
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
