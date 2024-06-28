import React from "react";
import styles from "./productsList.module.css";
import movies from "../../../movies.json";
import { useState } from "react";
import { MdArrowForwardIos } from "react-icons/md";
import { Link } from "react-router-dom";

const MovieList = () => {
  const [categoryFilter, setCategoryFilter] = useState("All Movies");
  const [sortByFilter, setSortByFilter] = useState("Featured");

  const handleSorByFilterChange = (event) => {
    setSortByFilter(event.target.value);
  };

  return (
    <div className={styles["products-list-wrapper"]}>
      <div className={styles["category-filter"]}>
        <hr />
        <div className={styles["filter-words"]}>
          <span
            onClick={() => setCategoryFilter("All Movies")}
            className={
              categoryFilter === "All Movies" ? styles["word-selected"] : ""
            }
          >
            ALL MOVIES
          </span>
          <span
            onClick={() => setCategoryFilter("Action")}
            className={
              categoryFilter === "Action" ? styles["word-selected"] : ""
            }
          >
            ACTION
          </span>
          <span
            onClick={() => setCategoryFilter("Comedy")}
            className={
              categoryFilter === "Comedy" ? styles["word-selected"] : ""
            }
          >
            COMEDY
          </span>
          <span
            onClick={() => setCategoryFilter("Horror")}
            className={
              categoryFilter === "Horror" ? styles["word-selected"] : ""
            }
          >
            HORROR
          </span>
          <span
            onClick={() => setCategoryFilter("Nostalgic")}
            className={
              categoryFilter === "Nostalgic" ? styles["word-selected"] : ""
            }
          >
            NOSTALGIC
          </span>
          <span
            onClick={() => setCategoryFilter("Romance")}
            className={
              categoryFilter === "Romance" ? styles["word-selected"] : ""
            }
          >
            ROMANCE
          </span>
          <span
            onClick={() => setCategoryFilter("War")}
            className={categoryFilter === "War" ? styles["word-selected"] : ""}
          >
            WAR
          </span>
        </div>
        <hr />
      </div>

      <div className={styles["category-path"]}>
        <span onClick={() => setCategoryFilter("All Movies")}>All Movies</span>
        {categoryFilter === "All Movies" ? (
          ""
        ) : (
          <div>
            <MdArrowForwardIos className={styles["arrow-icon"]} />
            <span>{categoryFilter}</span>
          </div>
        )}
      </div>

      <div className={styles["products-sort-by"]}>
        <div className={styles["category-name"]}>
          <h2>{categoryFilter}</h2>
        </div>
        <div className={styles["sort-by"]}>
          <span>Sort by</span>
          <select value={sortByFilter} onChange={handleSorByFilterChange}>
            <option value="Featured">Featured</option>
            <option value="High-low">Price, high to low</option>
            <option value="Low-high">Price, low to high</option>
          </select>
        </div>
      </div>
      <div className={styles["products-grid"]}>
        {categoryFilter === "All Movies" && sortByFilter === "Featured"
          ? movies.map((movie) => (
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
            ))
          : categoryFilter === "All Movies" && sortByFilter === "Low-high"
          ? movies
              .sort((a, b) => a.price - b.price)
              .map((movie) => (
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
              ))
          : categoryFilter === "All Movies" && sortByFilter === "High-low"
          ? movies
              .sort((a, b) => b.price - a.price)
              .map((movie) => (
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
              ))
          : categoryFilter !== "All Movies" && sortByFilter === "Featured"
          ? movies
              .filter((movie) => movie.type === categoryFilter)
              .map((movie) => (
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
              ))
          : categoryFilter !== "All Movies" && sortByFilter === "Low-high"
          ? movies
              .filter((movie) => movie.type === categoryFilter)
              .sort((a, b) => a.price - b.price)
              .map((movie) => (
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
              ))
          : movies
              .filter((movie) => movie.type === categoryFilter)
              .sort((a, b) => b.price - a.price)
              .map((movie) => (
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

export default MovieList;
