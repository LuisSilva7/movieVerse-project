import React from "react";
import styles from "./info.module.css";
import { BiMoviePlay, BiHappyAlt } from "react-icons/bi";
import { SlPresent } from "react-icons/sl";

const Info = () => {
  return (
    <div className={styles["info-wrapper"]}>
      <div className={styles["upper-text-wrapper"]}>
        <h1>WHY CHOOSE US ?</h1>
        <h4>
          GET THE BEST EXPERIENCE, BY BRINGING THE MAGIC OF MOVIES TO YOUR HOME
        </h4>
        <hr />
      </div>
      <div className={styles["bottom-text-wrapper"]}>
        <div className={styles["selection-wrapper"]}>
          <BiMoviePlay className={styles["selection-icon"]} />
          <h3>Selection</h3>
          <p>
            Discover a vast collection of movies, from the latest blockbusters
            to timeless classics, carefully curated for every taste. Our library
            is regularly updated to ensure you always find something new and
            exciting to watch. Whether you're in the mood for a light-hearted
            comedy, a deep drama, or an action-packed epic, we have something
            special waiting for you. Dive into a world of cinematic wonders and
            enjoy an unparalleled movie experience.
          </p>
        </div>
        <div className={styles["benefits-wrapper"]}>
          <SlPresent className={styles["benefits-icon"]} />
          <h3>Benefits</h3>
          <p>
            Take advantage of exclusive offers and discounts on special
            occasions. Our loyalty program ensures you get the best deals and
            rewards, tailored just for you. Enjoy seasonal promotions, special
            gifts, and members-only perks that enhance your movie-watching
            experience. Whether it's a holiday celebration or a milestone event,
            we're here to make your experience even more rewarding and
            enjoyable.
          </p>
        </div>
        <div className={styles["experience-wrapper"]}>
          <BiHappyAlt className={styles["experience-icon"]} />
          <h3>Experience</h3>
          <p>
            With our seamless purchasing process, high-quality streaming
            options, and excellent customer support, your movie nights will be
            unforgettable. Enjoy a hassle-free experience from start to finish,
            with easy navigation and quick access to your favorite films. Our
            top-notch streaming quality ensures you get the best visual and
            audio experience, making every movie night feel like a trip to the
            cinema. Plus, our dedicated customer support team is always ready to
            assist you, ensuring a smooth and enjoyable viewing experience every
            time.
          </p>
        </div>
      </div>
    </div>
  );
};

export default Info;
