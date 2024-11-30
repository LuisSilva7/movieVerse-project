import React from "react";
import Login from "../components/loginPage/Login";
import { useEffect } from "react";

const LoginPage = () => {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  return (
    <>
      <Login />
    </>
  );
};

export default LoginPage;
