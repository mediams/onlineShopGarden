FROM eclipse-temurin:17-alpine AS builder

WORKDIR /app
COPY . .

RUN chmod +x ./mvnw

RUN ./mvnw package -DskipTests

FROM eclipse-temurin:17-alpine
ENV SPRING_PROFILES_ACTIVE=docker
WORKDIR /app
COPY --from=builder /app/target/onlineShopGarden-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
