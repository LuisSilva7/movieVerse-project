import React from "react";
import styles from "./product.module.css";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

const Product = () => {
  const [movie, setMovie] = useState({});
  const [error, setError] = useState(null);
  const [movieInCart, setMovieInCart] = useState("ADD TO CART");
  const { id } = useParams();

  useEffect(() => {
    const fetchMovie = async () => {
      try {
        const response = await fetch(`/api/v1/movies/movie/${id}`, {
          method: "GET",
        });

        if (!response.ok) {
          throw new Error("Error fetching movies.");
        }

        const data = await response.json();
        setMovie(data.data);
        console.log(data);
      } catch (error) {
        setError(error.message);
      }
    };

    fetchMovie();
  }, []);

  /*const handleProduct = () => {
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
  };*/

  return (
    <div className={styles["product-wrapper"]}>
      {error && <p>{error}</p>}
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
          {/*<button onClick={handleProduct}>{movieInCart}</button>*/}
          <button>{movieInCart}</button>
        </div>
      </div>
    </div>
  );
};

export default Product;
