import React, { useEffect, useState } from "react";
import styles from "./popularProducts.module.css";
import { Link } from "react-router-dom";

const PopularProducts = () => {
  const [movies, setMovies] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchMovies = async () => {
      try {
        const response = await fetch("/api/v1/movies/popular", {
          method: "GET",
        });

        if (!response.ok) {
          throw new Error("Error fetching movies.");
        }

        const data = await response.json();
        setMovies(data.data);
      } catch (error) {
        setError(error.message);
      }
    };

    fetchMovies();
  }, []);

  return (
    <div className={styles["popular-products-wrapper"]}>
      <div className={styles["popular-section"]}>
        <h2 className={styles["popular-section-text"]}>Popular Movies</h2>
      </div>
      {error && <p>{error}</p>}
      <div className={styles["popular-products-grid"]}>
        {movies.map((movie) => (
          <div key={movie.id} className={styles["popular-products-grid-item"]}>
            <Link to={`/products/${movie.id}`}>
              <img
                src={`../../../movies-thumb/${movie.image}`} // Certifique-se de que o caminho da imagem está correto
                alt={movie.name}
              />
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
};

export default PopularProducts;
