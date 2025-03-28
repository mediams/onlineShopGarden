FROM eclipse-temurin:17-alpine

WORKDIR /app

COPY target/onlineShopGarden-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
