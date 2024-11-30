import React from "react";
import styles from "./navbar.module.css";
import { NavLink } from "react-router-dom";
import { PiShoppingCartThin } from "react-icons/pi";
import logoImage from "../../assets/images/movieVerse-logo-white.png";
import { useState, useEffect } from "react";

const Navbar = ({ route }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [scrolled, setScrolled] = useState(false);

  useEffect(() => {
    const accessToken = localStorage.getItem("accessToken");
    setIsLoggedIn(!!accessToken);
  }, []);

  useEffect(() => {
    if (route !== "/") {
      setScrolled(true);
    } else {
      setScrolled(false);
      const handleScroll = () => {
        const scrollY = window.scrollY;

        if (scrollY > 100) {
          setScrolled(true);
        } else {
          setScrolled(false);
        }
      };

      window.addEventListener("scroll", handleScroll);

      return () => {
        window.removeEventListener("scroll", handleScroll);
      };
    }
  }, [route]);

  const handleLogout = () => {
    localStorage.removeItem("accessToken");
    window.location.href = "/";
  };

  return (
    <div
      className={`${
        !scrolled ? styles["header-wrapper"] : styles["header-wrapper-scrolled"]
      }`}
    >
      <div className={styles["header-left-section"]}>
        <NavLink to="/">
          <img className={styles["site-logo"]} src={logoImage} />
        </NavLink>
      </div>

      <div className={styles["header-right-section"]}>
        <NavLink className={styles["home-link"]} to="/">
          HOME
        </NavLink>
        <NavLink className={styles["movies-link"]} to="/products">
          MOVIES
        </NavLink>
        <NavLink className={styles["about-link"]} to="/about">
          ABOUT
        </NavLink>
        <NavLink className={styles["cart-link"]} to="/cart">
          <PiShoppingCartThin className={styles["cart-icon"]} />
        </NavLink>
        {isLoggedIn ? (
          <NavLink className={styles["login-link"]} onClick={handleLogout}>
            LOGOUT
          </NavLink>
        ) : (
          <NavLink className={styles["login-link"]} to="/login">
            LOGIN
          </NavLink>
        )}
      </div>
    </div>
  );
};

export default Navbar;
