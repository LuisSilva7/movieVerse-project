# Backend

## How to Run the Project Locally

### Installation Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/LuisSilva7/movieVerse-project.git
   ```

2. Navigate to the project backend directory:

   ```bash
   cd movieVerse-project/movieVerse-backend
   ```

3. Run MySQL container:

   ```bash
   docker compose up -d
   ```

4. Run the server:

   ```bash
   mvn spring-boot:run
   ```

5. In order to use Stripe, you need to setup the api key in application.properties file and the movies Id.
