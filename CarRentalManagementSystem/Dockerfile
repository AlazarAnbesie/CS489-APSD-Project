FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY . /app

WORKDIR /app/CarRentalManagementSystem

RUN mvn clean package -DskipTests

FROM openjdk:17

WORKDIR /app

COPY --from=build /app/target/*.jar /app/CarRentalManagementApp.jar

CMD ["java", "-jar", "/app/CarRentalManagementApp.jar"]
