# Fase 1: Compilar la aplicación con Maven y Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar el archivo de dependencias y resolverlas
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar el proyecto
COPY src ./src
RUN mvn clean package -DskipTests

# Fase 2: Ejecutar la aplicación con OpenJDK 21
FROM eclipse-temurin:21-jdk AS runtime
WORKDIR /app

# Copiar el JAR desde la fase de construcción
COPY --from=build /app/target/reto-0.0.1.jar app.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
