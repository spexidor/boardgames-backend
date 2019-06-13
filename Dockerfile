# Alpine Linux with OpenJDK JRE
FROM adoptopenjdk/openjdk11
# copy WAR into image
COPY target/boardgames-backend-0.0.1-SNAPSHOT.jar /app.jar
# run application with this command line
CMD ["/usr/share/java", "-jar", "/app.jar"]