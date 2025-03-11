# Stage 1: Build the application
FROM 676206929160.dkr.ecr.us-east-1.amazonaws.com/ecs-test:3.9.6 AS build
WORKDIR /app

# Copy the project source code
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight runtime image
FROM eclipse-temurin:17-jre-alpine

# Create a non-root user (e.g., 'appuser') and set permissions
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar
EXPOSE 3000

# Change ownership of the app files to the non-root user
RUN chown -R appuser:appgroup /app

# Switch to the non-root user
USER appuser

# Run the application
CMD ["java", "-jar", "app.jar"]
