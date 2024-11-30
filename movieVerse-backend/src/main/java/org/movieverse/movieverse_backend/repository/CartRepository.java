package org.movieverse.movieverse_backend.repository;

import org.movieverse.movieverse_backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
