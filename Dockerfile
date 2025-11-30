FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Copiar archivos de configuración
COPY pom.xml .
COPY nbactions.xml .
# Copiar código fuente
COPY src ./src
# Compilar el proyecto
RUN mvn clean package -DskipTests
# Etapa 2: Ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copiar el JAR generado (Spring Boot)
COPY --from=build /app/target/*.jar app.jar
# Puerto que usa Render
EXPOSE 8080
# Variable de entorno para el puerto
ENV PORT=8080
# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]
