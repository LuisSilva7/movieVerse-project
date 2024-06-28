import React from "react";
import styles from "./suggestions.module.css";
import movies from "../../../movies.json";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";

const Suggestions = () => {
  const { id } = useParams();
  const movie = movies.find((m) => m.id == id);
  const moviesByCategory = movies
    .filter((m1) => m1.name != movie.name)
    .filter((m2) => m2.type == movie.type)
    .slice(0, 4);

  return (
    <div className={styles["suggestions-wrapper"]}>
      <h4>You may also like</h4>
      <div className={styles["products-grid"]}>
        {moviesByCategory.map((movie) => (
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
