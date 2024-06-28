import React from "react";
import { Outlet } from "react-router-dom";
import Navbar from "../components/shared/Navbar";
import Footer from "../components/shared/Footer";
import { useLocation } from "react-router-dom";

const MainLayout = () => {
  const location = useLocation().pathname;

  return (
    <>
      <Navbar route={location} />
      <Outlet />
      <Footer />
    </>
  );
};

export default MainLayout;
