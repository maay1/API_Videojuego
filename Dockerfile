FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app
# Copiar archivos del proyecto
COPY pom.xml .
COPY src ./src
# Construir el JAR
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copiar el JAR desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar
# Ejecutar la aplicacion
EXPOSE 8080
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]