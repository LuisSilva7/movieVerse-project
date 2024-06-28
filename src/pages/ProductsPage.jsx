import React from "react";
import ProductsList from "../components/productsPage/ProductsList";
import { useEffect } from "react";

const ProductsPage = () => {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <>
      <ProductsList />
    </>
  );
};

export default ProductsPage;
