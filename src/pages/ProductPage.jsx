import React from "react";
import Product from "../components/productPage/Product";
import Suggestions from "../components/productPage/Suggestions";
import { useEffect } from "react";

const ProductPage = () => {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <>
      <Product />
      <Suggestions />
    </>
  );
};

export default ProductPage;
