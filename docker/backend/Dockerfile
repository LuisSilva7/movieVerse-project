FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:17
ARG APP_VERSION=1.0.0

WORKDIR /app
COPY --from=build /build/target/movieverse-backend-*.jar /app/

EXPOSE 8888

ENV DB_URL=jdbc:mysql://mysql_movieVerse:3306/movieVerse_db
ENV JAR_VERSION=${APP_VERSION}

CMD java -jar -Dspring.datasource.url=${DB_URL} movieverse-backend-${JAR_VERSION}.jar