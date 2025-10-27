# 1. Etapa de build: compilar o JAR
FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar arquivos do projeto
COPY pom.xml .
COPY src ./src

# Compilar o JAR
RUN mvn clean package -DskipTests

# 2. Etapa final: imagem leve com Java
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar o JAR da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Copiar o arquivo .env para dentro do container
# Ele deve definir: DB_NAME, DB_USER, DB_PASSWORD, SPRING_PROFILES_ACTIVE

# Expor porta
EXPOSE 8080

# Comando para rodar a aplicação usando o perfil definido no .env
ENTRYPOINT ["sh", "-c", "java -XX:-UseContainerSupport -jar app.jar \
    --spring.profiles.active=$SPRING_PROFILES_ACTIVE \
    --management.metrics.binders.processor.enabled=false \
    --management.metrics.binders.tomcat.enabled=false"]
