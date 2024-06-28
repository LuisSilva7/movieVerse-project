import React from "react";
import styles from "./hero.module.css";

const Hero = () => {
  return (
    <div className={styles["hero-wrapper"]}>
      <div className={styles["hero"]}>
        <h1 className={styles["hero-slogan"]}>
          Explore the World of Films: Where Every Scene Tells a Story!
        </h1>
        <hr className={styles["hero-line"]} />
        <h3 className={styles["hero-welcome"]}>Welcome to MovieVerse</h3>
      </div>
    </div>
  );
};

export default Hero;
