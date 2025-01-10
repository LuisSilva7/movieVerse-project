# MovieVerse - Ecommerce Shop

MovieVerse is an e-commerce platform designed for online movie shopping. It provides a simple interface with filtering options to browse a large collection of films and includes a secure payment system for purchases.

## Features

- **User Authentication**: Users can log in and log out of their accounts, providing access to purchasing features.
- **Home Page with Popular Movies**: Users are greeted with a curated list of popular movies.
- **Pagination for All Movies**: Browse through a vast collection of movies with easy pagination.
- **Filter by Price**: Users can filter movies based on their price, selecting either the highest to lowest or the lowest to highest prices.
- **Filter by Movie Genre**: Users can filter movies by genre, allowing them to find movies that suit their tastes.
- **Movie Detail Page**: Clicking on a movie reveals detailed information, including description, price, rating, and more.
- **Suggested Movies**: After opening a specific movie, users are shown suggested films based on the same category.
- **Add and Remove from Cart**: Users can easily add movies to their cart and remove them as needed.
- **About Page**: A dedicated about page offering information about the platform.
- **Stripe Payment Integration**: Securely purchase movies with Stripe's payment system.

## Technologies Used

- **Frontend**:
  - **React** - JavaScript library for building user interfaces.
  - **HTML** - Structure of the web application.
  - **CSS** - For styling the website.
  - **JavaScript** - Used for client-side logic and interactivity.

- **Backend**:
  - **Docker** - A containerization platform used to package and deploy the application in lightweight, isolated environments, ensuring consistency across different environments.
  - **Java** - Programming language used for backend development.
  - **Spring Boot** - Framework for building the backend API and handling HTTP requests.
  - **Spring Data JPA** - Simplifies database interaction by providing an abstraction layer for managing data persistence.
  - **Spring Security** - Provides user authentication and authorization, securing an application.
  - **Spring Validation** - A framework used for validating input data, ensuring that the data received by the application meets the defined rules.
  - **MySQL** - Relational database for storing movie, user, and more.
  - **JWT (JSON Web Tokens)** - For user authentication and secure communication between the frontend and backend.
  - **Stripe** - Payment processing system for handling movie purchases.

## How to Run the Project Locally

### Installation Steps

1. **Clone the repository:**

   ```bash
   git clone https://github.com/LuisSilva7/movieVerse-project.git
   ```

2. **Navigate to the project backend directory:**

   ```bash
   cd movieVerse-project/movieVerse-backend
   ```

3. **Run MySQL container:**

   ```bash
   docker compose up -d
   ```

4. **Run the backend server:**

   ```bash
   mvn spring-boot:run
   ```

5. **Stripe correct usage:**
   In order to use Stripe, you need to setup the api key in application.properties file and the movies Id.

6. **Navigate to the project frontend directory:**

   ```bash
   cd ../movieVerse-frontend
   ```

7. **Install project dependencies:**

   ```bash
   npm install
   ```

8. **Start the development server:**

   ```bash
   npm run dev
   ```

9. **Clear the LocalStorage:**
   Clear your LocalStorage to make sure everything is working.

The application will be available at [http://localhost:5000](http://localhost:5000) in your browser.

### Docker Setup (Optional)

If you prefer to run the application inside a Docker container, follow these steps:

1. **Navigate to the project backend directory:**

   ```bash
   cd movieVerse-project/movieVerse-backend
   ```

2. **Build the Docker backend image:**

   ```bash
   docker build -t movieverse-backend-project -f ../docker-movieverse/backend/Dockerfile .
   ```

3. **Navigate to the project frontend directory:**

   ```bash
   cd ../movieVerse-frontend
   ```

4. **Build the Docker frontend image:**

   ```bash
   docker build -t movieverse-frontend-project -f ../docker-movieverse/frontend/Dockerfile .
   ```

5. **Run all containers:**

   ```bash
   docker compose -f ../docker-movieverse/docker-compose.yml up -d
   ```

6. **Stripe correct usage:**
   In order to use Stripe, you need to setup the api key in application.properties file and the movies Id.

7. **Clear the LocalStorage:**
   Clear your LocalStorage to make sure everything is working.

The application will be available at [http://localhost:8080](http://localhost:8080) in your browser.

## Screenshots

### Home Page

![Home Page](screenshots/home.jpg)

### About Page

![About Page](screenshots/about.jpg)

### Movies Page

![Movies Page](screenshots/movies.jpg)

### Movie Page

![Movie Page](screenshots/movie.jpg)

### Cart Page

![Cart Page](screenshots/cart.jpg)

### Stripe Page

![Stripe Page](screenshots/stripe.jpg)

### Maintainer

- **Luis Silva** (Owner/Developer)
