import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5000,
    proxy: {
      "/api": {
        target: process.env.BACKEND_URL || "http://localhost:8888",
        changeOrigin: true,
      },
    },
  },
});
