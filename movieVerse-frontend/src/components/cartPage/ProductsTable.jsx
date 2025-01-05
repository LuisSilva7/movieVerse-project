import React, { useState, useEffect } from "react";
import styles from "./productsTable.module.css";
import { Link, useNavigate } from "react-router-dom";

const ProductsTable = () => {
  const [cartMovies, setCartMovies] = useState([]);
  const [cartTotalAmount, setCartTotalAmount] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const accessToken = localStorage.getItem("accessToken");

    if (!accessToken) {
      navigate("/login");
      return;
    }

    const fetchTotalAmount = async () => {
      try {
        const response = await fetch(
          "http://localhost:8888/api/v1/carts/totalAmount",
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${accessToken}`,
            },
          }
        );

        if (!response.ok) {
          throw new Error("Error fetching movies.");
        }

        const data = await response.json();
        setCartTotalAmount(data.data);
      } catch (error) {
        console.error("Error fetching total amount:", error.message);
      }
    };

    const fetchMovies = async () => {
      try {
        const response = await fetch("http://localhost:8888/api/v1/carts/", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${accessToken}`,
          },
        });

        if (!response.ok) {
          throw new Error("Error fetching movies.");
        }

        const data = await response.json();
        setCartMovies(data.data);
      } catch (error) {
        console.error("Error fetching movies:", error.message);
      }
    };

    fetchTotalAmount();
    fetchMovies();
  }, [navigate]);

  const redirectToMovies = () => {
    navigate("/products");
  };

  const removeProduct = async (movieId, price) => {
    const accessToken = localStorage.getItem("accessToken");

    if (!accessToken) {
      navigate("/login");
      return;
    }

    try {
      const url = `http://localhost:8888/api/v1/carts/${movieId}`;

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

      setCartMovies((prevMovies) =>
        prevMovies.filter((movie) => movie.id !== movieId)
      );

      setCartTotalAmount(
        (prevCartAmount) => Math.round((prevCartAmount - price) * 100) / 100
      );

      alert("Movie removed from cart!");
    } catch (error) {
      console.error("Error:", error);
      alert("Failed to remove movie from cart. Please try again.");
    }
  };

  const checkout = async () => {
    const accessToken = localStorage.getItem("accessToken");

    if (!accessToken) {
      navigate("/login");
      return;
    }

    try {
      const response = await fetch(
        "http://localhost:8888/api/v1/carts/create-checkout-session",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );

      if (!response.ok) {
        throw new Error("Error creating checkout session!");
      }

      const data = await response.json();

      window.open(data.data);
    } catch (error) {
      console.error("Error creating checkout session:", error);
      alert("Error creating checkout session! Please, try again.");
    }
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
              <div key={cartMovie.id} className={styles["product-row"]}>
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
                      <h5
                        onClick={() =>
                          removeProduct(cartMovie.id, cartMovie.price)
                        }
                      >
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
                  <span>{cartTotalAmount} €</span>
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
