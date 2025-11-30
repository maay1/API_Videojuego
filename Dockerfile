FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Copiar archivos del proyecto
COPY . .
# Dar permisos de ejecucion a mvnw
RUN chmod +x mvnw
# Construir el JAR
RUN ./mvnw clean package -DskipTests
# Encontrar y copiar el JAR
RUN find target -name "*.jar" -exec cp {} app.jar \;
# Ejecutar la aplicacion
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]