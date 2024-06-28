import React from "react";
import Hero from "../components/homePage/Hero";
import PopularProducts from "../components/homePage/PopularProducts";
import { useEffect } from "react";

const HomePage = () => {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  return (
    <>
      <Hero />
      <PopularProducts />
    </>
  );
};

export default HomePage;
