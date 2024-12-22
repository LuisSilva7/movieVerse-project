import React from "react";
import styles from "./productsList.module.css";
import { useEffect, useState } from "react";
import { MdArrowBackIosNew, MdArrowForwardIos } from "react-icons/md";
import { Link } from "react-router-dom";

const MovieList = () => {
  const [movies, setMovies] = useState([]);
  const [error, setError] = useState(null);
  const [categoryFilter, setCategoryFilter] = useState("All Movies");
  const [sortByFilter, setSortByFilter] = useState("Featured");
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [isFirst, setIsFirst] = useState(true);
  const [isLast, setIsLast] = useState(false);

  useEffect(() => {
    fetchMovies();
  }, []);

  const fetchMovies = async (page = 0) => {
    try {
      const response = await fetch(`/api/v1/movies/?page=${page}&size=12`, {
        method: "GET",
      });

      if (!response.ok) {
        throw new Error("Error fetching movies.");
      }

      const data = await response.json();

      setMovies(data.data.content);
      console.log("antes de guardar: " + page);
      setPage(data.data.number);
      console.log("depois de guardar: " + page);
      setTotalPages(data.data.totalPages);
      setIsFirst(data.data.first);
      setIsLast(data.data.last);
    } catch (error) {
      console.error("Error fetching movies:", error);
    }
  };

  const fetchMoviesByCategory = async (category) => {
    if (category === "All Movies") {
      try {
        const response = await fetch("api/v1/movies/", {
          method: "GET",
        });

        if (!response.ok) {
          throw new Error("Error fetching movies.");
        }

        const data = await response.json();
        setMovies(data.data.content);
      } catch (error) {
        setError(error.message);
      }
    } else {
      try {
        const response = await fetch(`/api/v1/movies/type/${category}`, {
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
    }
  };

  const handleCategoryClick = (category) => {
    setCategoryFilter(category);
    fetchMoviesByCategory(category);
  };

  const handleSorByFilterChange = (event) => {
    setSortByFilter(event.target.value);
  };

  const handleNextPage = () => {
    if (!isLast) {
      fetchMovies(page + 1);
    }
  };

  const handlePreviousPage = () => {
    if (!isFirst) {
      fetchMovies(page - 1);
    }
  };

  return (
    <div className={styles["products-list-wrapper"]}>
      <div className={styles["category-filter"]}>
        <hr />
        <div className={styles["filter-words"]}>
          <span
            onClick={() => handleCategoryClick("All Movies")}
            className={
              categoryFilter === "All Movies" ? styles["word-selected"] : ""
            }
          >
            ALL MOVIES
          </span>
          <span
            onClick={() => handleCategoryClick("Action")}
            className={
              categoryFilter === "Action" ? styles["word-selected"] : ""
            }
          >
            ACTION
          </span>
          <span
            onClick={() => handleCategoryClick("Comedy")}
            className={
              categoryFilter === "Comedy" ? styles["word-selected"] : ""
            }
          >
            COMEDY
          </span>
          <span
            onClick={() => handleCategoryClick("Horror")}
            className={
              categoryFilter === "Horror" ? styles["word-selected"] : ""
            }
          >
            HORROR
          </span>
          <span
            onClick={() => handleCategoryClick("Nostalgic")}
            className={
              categoryFilter === "Nostalgic" ? styles["word-selected"] : ""
            }
          >
            NOSTALGIC
          </span>
          <span
            onClick={() => handleCategoryClick("Romance")}
            className={
              categoryFilter === "Romance" ? styles["word-selected"] : ""
            }
          >
            ROMANCE
          </span>
          <span
            onClick={() => handleCategoryClick("War")}
            className={categoryFilter === "War" ? styles["word-selected"] : ""}
          >
            WAR
          </span>
        </div>
        <hr />
      </div>

      <div className={styles["category-path"]}>
        <span onClick={() => handleCategoryClick("All Movies")}>
          All Movies
        </span>
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
      {error && <p>{error}</p>}
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
          : categoryFilter !== "All Movies" && sortByFilter === "Low-high"
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
          : movies
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
      <div className={styles["pagination-buttons"]}>
        {categoryFilter === "All Movies" && (
          <>
            {page > 0 && (
              <button
                className={styles["nav-button"]}
                onClick={handlePreviousPage}
                disabled={isFirst}
              >
                <MdArrowBackIosNew size={14} />{" "}
              </button>
            )}

            <span className={styles["page-info"]}>
              Page {page + 1} of {totalPages}
            </span>

            {page < totalPages - 1 && (
              <button
                className={styles["nav-button"]}
                onClick={handleNextPage}
                disabled={isLast}
              >
                <MdArrowForwardIos size={14} />{" "}
              </button>
            )}
          </>
        )}
      </div>
    </div>
  );
};

export default MovieList;
