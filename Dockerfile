FROM maven:3.9.9-amazoncorretto-8-debian AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:24-ea-11-oracle
WORKDIR /app
COPY --from=build /app/target/crafthive-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java","-jar","app.jar" ]
