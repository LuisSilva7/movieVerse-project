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

  const sortMoviesByPrice = (movies, sortBy) => {
    let sortedMovies = [...movies];

    if (sortBy === "High-low") {
      sortedMovies.sort((a, b) => b.price - a.price);
    } else if (sortBy === "Low-high") {
      sortedMovies.sort((a, b) => a.price - b.price);
    }

    return sortedMovies;
  };

  const fetchMovies = async (page = 0) => {
    try {
      const response = await fetch(
        `http://localhost:8888/api/v1/movies/?page=${page}&size=12`,
        {
          method: "GET",
        }
      );

      if (!response.ok) {
        throw new Error("Error fetching movies.");
      }

      const data = await response.json();

      const sortedMovies = sortMoviesByPrice(data.data.content, sortByFilter);

      setMovies(sortedMovies);
      setPage(data.data.number);
      setTotalPages(data.data.totalPages);
      setIsFirst(data.data.first);
      setIsLast(data.data.last);
    } catch (error) {
      console.error("Error fetching movies:", error);
    }
  };

  const fetchMoviesByCategory = async (category) => {
    if (category === "All Movies") {
      fetchMovies(page);
    } else {
      try {
        const response = await fetch(
          `http://localhost:8888/api/v1/movies/type/${category}`,
          {
            method: "GET",
          }
        );

        if (!response.ok) {
          throw new Error("Error fetching movies.");
        }

        const data = await response.json();

        const sortedMovies = sortMoviesByPrice(data.data, sortByFilter);

        setMovies(sortedMovies);
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
    const selectedSort = event.target.value;
    setSortByFilter(selectedSort);

    const sortedMovies = sortMoviesByPrice(movies, selectedSort);
    setMovies(sortedMovies);
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
          {[
            "All Movies",
            "Action",
            "Comedy",
            "Horror",
            "Nostalgic",
            "Romance",
            "War",
          ].map((category) => (
            <span
              key={category}
              onClick={() => handleCategoryClick(category)}
              className={
                categoryFilter === category ? styles["word-selected"] : ""
              }
            >
              {category.toUpperCase()}
            </span>
          ))}
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
          <span className={styles["sort-by-label"]}>Sort by</span>{" "}
          <select value={sortByFilter} onChange={handleSorByFilterChange}>
            <option value="Featured">Featured</option>
            <option value="High-low">Price, high to low</option>
            <option value="Low-high">Price, low to high</option>
          </select>
        </div>
      </div>

      {error && <p>{error}</p>}
      <div className={styles["products-grid"]}>
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
