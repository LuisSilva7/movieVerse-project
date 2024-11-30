import React from "react";
import styles from "./productsTable.module.css";
import { useState, useEffect } from "react";
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
        const response = await fetch("/api/v1/cart/totalAmount", {
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
        setCartTotalAmount(data.data);
        console.log(data);
      } catch (error) {
        setError(error.message);
      }
    };

    const fetchMovies = async () => {
      try {
        const response = await fetch("/api/v1/cart/get-movies", {
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
        console.log(data);
      } catch (error) {
        setError(error.message);
      }
    };

    fetchTotalAmount();
    fetchMovies();
  }, [cartMovies]);

  const redirectToMovies = () => {
    navigate("/products");
  };

  const totalPrice = () => {
    let total = 0;
    cartMovies.forEach((cartMovie) => {
      total += cartMovie.price * 100;
    });

    return (total / 100).toFixed(2);
  };

  const removeProduct = async (movieId) => {
    const accessToken = localStorage.getItem("accessToken");

    if (!accessToken) {
      navigate("/login");
      return;
    }

    try {
      const url = `/api/v1/cart/remove-movie/${movieId}`;

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
      const response = await fetch("/api/v1/checkout/create-checkout-session", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${accessToken}`,
        },
      });

      if (!response.ok) {
        throw new Error("Falha ao criar a sessão de checkout");
      }

      const data = await response.text();
      console.log("Sessão de checkout criada com sucesso:", data);

      const startIdx = data.indexOf("=") + 1;
      const endIdx = data.indexOf("}");
      const url = data.substring(startIdx, endIdx);
      window.open(url);
    } catch (error) {
      console.error("Erro ao criar a sessão de checkout:", error);
      alert("Falha ao criar a sessão de checkout. Tente novamente.");
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
