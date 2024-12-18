import React from "react";
import styles from "./product.module.css";
import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

const Product = () => {
  const [movie, setMovie] = useState({});
  const [error, setError] = useState(null);
  const [movieInCart, setMovieInCart] = useState("ADD TO CART");
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchMovie = async () => {
      try {
        const response = await fetch(`/api/v1/movies/${id}`, {
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
  }, [id]);

  useEffect(() => {
    if (!movie.name) return;

    const accessToken = localStorage.getItem("accessToken");

    if (!accessToken) {
      navigate("/login");
      return;
    }

    const fetchMovies = async () => {
      try {
        const response = await fetch("/api/v1/carts/", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${accessToken}`,
          },
        });

        if (!response.ok) {
          if (response.status === 404) {
            setMovieInCart("ADD TO CART");
            return;
          }
          throw new Error("Error fetching cart data.");
        }

        const data = await response.json();

        if (data.data && data.data.length > 0) {
          const isInCart = data.data.some((m) => m.name === movie.name);
          setMovieInCart(isInCart ? "REMOVE FROM CART" : "ADD TO CART");
        } else {
          setMovieInCart("ADD TO CART");
        }

        console.log(data);
      } catch (error) {
        setError(error.message);
        console.error("Error:", error);
      }
    };

    fetchMovies();
  }, [movie]);

  const handleProduct = async () => {
    const accessToken = localStorage.getItem("accessToken");

    if (!accessToken) {
      navigate("/login");
      return;
    }

    if (movieInCart === "ADD TO CART") {
      try {
        const url = `/api/v1/carts/${id}`;

        const response = await fetch(url, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${accessToken}`,
          },
        });

        if (!response.ok) {
          throw new Error("Failed to add movie to cart");
        }

        const data = await response.json();
        console.log(data);

        setMovieInCart("REMOVE FROM CART");

        alert("Movie added to cart!");
      } catch (error) {
        console.error("Error:", error);
        alert("Failed to add movie to cart. Please try again.");
      }
    } else {
      try {
        const url = `/api/v1/carts/${id}`;

        const response = await fetch(url, {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${accessToken}`,
          },
        });

        if (!response.ok) {
          throw new Error("Failed to remove movie from cart");
        }

        const data = await response.json();
        console.log(data);

        setMovieInCart("ADD TO CART");

        alert("Movie removed from cart!");
      } catch (error) {
        console.error("Error:", error);
        alert("Failed to remove movie from cart. Please try again.");
      }
    }

    /*if (movieInCart == "REMOVE FROM CART") {
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
    localStorage.setItem("cartMovies", JSON.stringify(cartMovies));*/
  };

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
          {<button onClick={handleProduct}>{movieInCart}</button>}
        </div>
      </div>
    </div>
  );
};

export default Product;
