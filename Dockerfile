# Etapa 1: build
FROM maven:3.9.6-eclipse-temurin-17 AS build

RUN apt-get update && apt-get install -y openssl && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mkdir -p src/main/resources/certs && \
    cd src/main/resources/certs && \
    openssl genrsa -out keypair.pem 2048 && \
    openssl rsa -in keypair.pem -pubout -out public.pem && \
    openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt \
      -in keypair.pem -out private.pem && \
    rm keypair.pem
RUN mvn clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
