import React, { useState } from "react";
import styles from "./login.module.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch(
        "http://localhost:8888/api/v1/auth/authenticate",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ email, password }),
        }
      );

      if (response.ok) {
        const data = await response.json();
        localStorage.setItem("accessToken", data.data.token);
        alert("Login successful!");
        window.location.href = "/products";
      } else {
        setError("Invalid email or password");
      }
    } catch (err) {
      console.error("Error during login:", err);
      setError("Something went wrong. Please try again later.");
    }
  };

  return (
    <div className={styles["login-wrapper"]}>
      <h1 className={styles["login-title"]}>Login</h1>
      <form className={styles["login-form"]} onSubmit={handleSubmit}>
        <div className={styles["form-group"]}>
          <label htmlFor="email">Email</label>
          <input
            type="text"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Enter your email"
            required
          />
        </div>
        <div className={styles["form-group"]}>
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Enter your password"
            required
          />
        </div>
        <button className={styles["login-button"]} type="submit">
          Login
        </button>
      </form>
      <div className={styles["default-credentials"]}>
        <p>Default email: silva@gmail.com</p>
        <p>Default password: 12345</p>
      </div>
    </div>
  );
};

export default Login;
