# ==========================================
# Etapa 1: Construcción de la aplicación
# ==========================================
FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar el código fuente
COPY . .

# Compilar y empaquetar la aplicación
RUN mvn clean package -DskipTests

# ==========================================
# Etapa 2: Ejecución de la aplicación
# ==========================================
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copiar el JAR generado en la etapa de construcción
COPY --from=build /app/target/lab2ci-cd.jar lab2ci-cd.jar

# Puerto de la aplicación
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "lab2ci-cd.jar"]