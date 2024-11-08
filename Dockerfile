#Build stage
FROM eclipse-temurin:17

WORKDIR /app
COPY . .

EXPOSE 8080

CMD ["./gradlew", "clean", "bootRun", "-x", "test"]