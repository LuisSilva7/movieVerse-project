import React from "react";
import styles from "./product.module.css";
import movies from "../../../movies.json";
import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";

const Product = () => {
  const { id } = useParams();
  const movie = movies.find((m) => m.id == id);
  let cartMovies = [];

  const [movieInCart, setMovieInCart] = useState("ADD TO CART");

  useEffect(() => {
    setMovieInCart("ADD TO CART");

    if (localStorage.getItem("cartMovies")) {
      cartMovies = JSON.parse(localStorage.getItem("cartMovies"));
      const isMovieInCart = cartMovies.some(
        (cartMovie) => cartMovie == movie.id
      );

      if (isMovieInCart) {
        setMovieInCart("REMOVE FROM CART");
      }
    }
  }, [movie]);

  const handleProduct = () => {
    if (movieInCart == "REMOVE FROM CART") {
      cartMovies = JSON.parse(localStorage.getItem("cartMovies"));
      let newCartMovies = cartMovies.filter((m) => m != movie.id);
      localStorage.setItem("cartMovies", JSON.stringify(newCartMovies));
      setMovieInCart("ADD TO CART");
      return;
    }

    let isMovieInCart = false;

    if (localStorage.getItem("cartMovies")) {
      cartMovies = JSON.parse(localStorage.getItem("cartMovies"));
      isMovieInCart = cartMovies.some((cartMovie) => cartMovie == movie.id);
    }

    if (!isMovieInCart) {
      cartMovies.push(movie.id);
      setMovieInCart("REMOVE FROM CART");
    }
    localStorage.setItem("cartMovies", JSON.stringify(cartMovies));
  };

  return (
    <div className={styles["product-wrapper"]}>
      <img
        src={`../../../movies-vertical/${movie.imageVertical}`}
        alt={movie.name}
      />
      <div className={styles["movie-info"]}>
        <div className={styles["info-top-section"]}>
          <h1>{movie.name}</h1>
          <p>{movie.description}</p>
          <p>
            IMDb {movie.rating} <span>{movie.duration} min</span>
          </p>
          <p>Category - {movie.type}</p>
        </div>
        <div className={styles["info-bottom-section"]}>
          <p>{movie.price} €</p>
          <button onClick={handleProduct}>{movieInCart}</button>
        </div>
      </div>
    </div>
  );
};

export default Product;
