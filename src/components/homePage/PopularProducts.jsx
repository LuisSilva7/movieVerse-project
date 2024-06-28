import React from "react";
import styles from "./popularProducts.module.css";
import movies from "../../../movies.json";
import { Link } from "react-router-dom";

const PopularProducts = () => {
  return (
    <div className={styles["popular-products-wrapper"]}>
      <div className={styles["popular-section"]}>
        <h2 className={styles["popular-section-text"]}>Popular Movies</h2>
      </div>
      <div className={styles["popular-products-grid"]}>
        {movies.slice(0, 12).map((movie) => (
          <div key={movie.id} className={styles["popular-producs-grid-item"]}>
            <Link to={`/products/${movie.id}`}>
              <img
                src={`../../../movies-thumb/${movie.image}`}
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
