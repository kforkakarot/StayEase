FROM openjdk:17-oracle

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the project directory
COPY ./out/artifacts/StayEase_jar/StayEase.jar ./app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]
