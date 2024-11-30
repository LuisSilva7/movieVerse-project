import React from "react";
import Info from "../components/aboutPage/Info";
import { useEffect } from "react";

const AboutPage = () => {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  return (
    <>
      <Info />
    </>
  );
};

export default AboutPage;
