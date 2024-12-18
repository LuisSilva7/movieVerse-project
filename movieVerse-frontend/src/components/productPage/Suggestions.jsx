import React from "react";
import styles from "./suggestions.module.css";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";

const Suggestions = () => {
  const [movies, setMovies] = useState([]);
  const [error, setError] = useState(null);
  const { id } = useParams();

  useEffect(() => {
    const fetchMovies = async () => {
      try {
        const response = await fetch(`/api/v1/movies/${id}/suggestions`, {
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
  }, [id]);

  return (
    <div className={styles["suggestions-wrapper"]}>
      <h4>You may also like</h4>
      <div className={styles["products-grid"]}>
        {error && <p>{error}</p>}
        {movies.map((movie) => (
          <div key={movie.id} className={styles["producs-grid-item"]}>
            <Link to={`/products/${movie.id}`}>
              <img
                src={`../../../movies-thumb/${movie.image}`}
                alt={movie.name}
              />
            </Link>
            <h5>{movie.name}</h5>
            <h6>{movie.price} €</h6>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Suggestions;
