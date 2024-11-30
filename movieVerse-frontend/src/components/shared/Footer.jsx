import React from "react";
import styles from "./footer.module.css";
import { MdOutlineKeyboardArrowUp } from "react-icons/md";
import { FaFacebookF, FaTwitter, FaInstagram } from "react-icons/fa";
import { RiInstagramFill } from "react-icons/ri";

const Footer = () => {
  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  return (
    <div className={styles["footer-wrapper"]}>
      <MdOutlineKeyboardArrowUp
        className={styles["arrow-icon"]}
        onClick={scrollToTop}
      />
      <p className={styles["text-top"]}>BACK TO TOP</p>
      <div className={styles["social-icons"]}>
        <FaFacebookF className={styles["facebook-icon"]} />
        <FaTwitter className={styles["twitter-icon"]} />
        <RiInstagramFill className={styles["instagram-icon"]} />
      </div>
      <p className={styles["text-rights"]}>
        All Rights Reserved. © 2024 MovieVerse
      </p>
    </div>
  );
};

export default Footer;
