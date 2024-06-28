import React from "react";
import styles from "./productsTable.module.css";
import movies from "../../../movies.json";
import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";

const ProductsTable = () => {
  const [cartMovies, setCartMovies] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    if (localStorage.getItem("cartMovies")) {
      const cartMoviesId = JSON.parse(localStorage.getItem("cartMovies"));
      const filteredCartMovies = movies.filter((movie) =>
        cartMoviesId.includes(movie.id)
      );

      setCartMovies(filteredCartMovies);
    }
  }, []);

  const redirectToMovies = () => {
    navigate("/products");
  };

  const totalPrice = () => {
    let total = 0;
    cartMovies.forEach((cartMovie) => {
      total += cartMovie.price * 100;
    });

    return total / 100;
  };

  const removeProduct = (movieId) => {
    if (localStorage.getItem("cartMovies")) {
      const cartMoviesId = JSON.parse(localStorage.getItem("cartMovies"));
      let newCartMovies = cartMoviesId.filter((m) => m != movieId);
      localStorage.setItem("cartMovies", JSON.stringify(newCartMovies));

      setCartMovies([]);
    }
  };

  const checkout = async () => {
    await fetch("http://localhost:4000/checkout", {
      method: "POST",
      headers: {
        "Content-type": "application/json",
      },
      body: JSON.stringify({
        items: cartMovies,
      }),
    })
      .then((response) => {
        return response.json();
      })
      .then(async (response) => {
        if (response.url) {
          setCartMovies([]);

          localStorage.setItem("cartMovies", JSON.stringify([]));

          window.location.assign(response.url);
        }
      });
  };

  return (
    <div className={styles["wrapper"]}>
      <div className={styles["products-table-wrapper"]}>
        <h1>Your Cart</h1>
        {cartMovies.length !== 0 ? (
          <div className={styles["products-table"]}>
            <div className={styles["products-table-top"]}>
              <span>Price</span>
            </div>
            <hr />
            {cartMovies.map((cartMovie) => (
              <div key="cartMovie" className={styles["product-row"]}>
                <div className={styles["product"]}>
                  <div className={styles["left-section"]}>
                    <div className={styles["image-wrapper"]}>
                      <Link to={`/products/${cartMovie.id}`}>
                        <img
                          src={`../../../movies-thumb/${cartMovie.image}`}
                          alt={cartMovie.name}
                        />
                      </Link>
                    </div>
                    <div className={styles["product-info"]}>
                      <Link
                        className={styles["product-info-link"]}
                        to={`/products/${cartMovie.id}`}
                      >
                        <h3>{cartMovie.name}</h3>
                      </Link>
                      <h4>IMDb {cartMovie.rating}</h4>
                      <h5 onClick={() => removeProduct(cartMovie.id)}>
                        Eliminar
                      </h5>
                    </div>
                  </div>
                  <div className={styles["right-section"]}>
                    <p>{cartMovie.price} €</p>
                  </div>
                </div>
                <hr />
              </div>
            ))}
            <div className={styles["products-table-bottom-wrapper"]}>
              <div className={styles["test-data"]}>
                <p>Test Account</p>
                <p>Email: test@gmail.com</p>
                <p>Card Number: 4242 4242 4242 4242</p>
                <p>MM/YY: 12/34</p>
                <p>CVC: 123</p>
                <p>Name: Test</p>
              </div>
              <div className={styles["products-table-bottom"]}>
                <div className={styles["products-final-price"]}>
                  <p>Total</p>
                  <span>{totalPrice()} €</span>
                </div>
                <button onClick={checkout}>CHECKOUT</button>
              </div>
            </div>
          </div>
        ) : (
          <div className={styles["empty-table"]}>
            <h3>Your cart is currently empty.</h3>
            <button onClick={redirectToMovies}>LET'S GO SHOPPING</button>
          </div>
        )}
      </div>
    </div>
  );
};

export default ProductsTable;
