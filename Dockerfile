# Stage 1: build usando Gradle com JDK 21 (Gradle 8.14.2)
FROM gradle:8.14.2-jdk21 AS builder

WORKDIR /app

# Copia código fonte e arquivos de build
COPY . .

# Compila e empacota o projeto (ajusta o target conforme necessário)
RUN gradle clean build -x test --no-daemon --info

# Stage 2: imagem final com JDK leve
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia somente o artefato JAR gerado
COPY --from=builder /app/build/libs/app.jar app.jar

EXPOSE 30001

ENTRYPOINT ["java", "-jar", "app.jar"]

#docker build \
#  --build-arg GRADLE_USER_HOME=/home/gradle/.gradle \
#  --mount type=cache,target=/home/gradle/.gradle \
#  -t seu-usuario/seu-app .

#docker login
#docker push thalessantanna/demo-health-database:latest

#docker buildx create --use
#docker buildx build --platform linux/amd64,linux/arm64 \
#  -t thalessantanna/demo-health-database:latest \
#  --push .
