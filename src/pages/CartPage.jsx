import React from "react";
import ProductsTable from "../components/cartPage/ProductsTable";
import { useEffect } from "react";

const CartPage = () => {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  return (
    <>
      <ProductsTable />
    </>
  );
};

export default CartPage;
