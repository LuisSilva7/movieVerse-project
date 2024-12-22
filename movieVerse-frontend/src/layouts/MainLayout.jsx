import React from "react";
import { Outlet } from "react-router-dom";
import Navbar from "../components/shared/Navbar";
import Footer from "../components/shared/Footer";
import { useLocation } from "react-router-dom";
import styles from "../components/shared/layout.module.css";

const MainLayout = () => {
  const location = useLocation().pathname;

  return (
    <div className={styles.layout}>
      <Navbar route={location} />
      <div className={styles.content}>
        <Outlet />
      </div>
      <Footer />
    </div>
  );
};

export default MainLayout;
