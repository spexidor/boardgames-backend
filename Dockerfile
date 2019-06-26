# Alpine Linux with OpenJDK JRE
#FROM adoptopenjdk/openjdk11
# copy WAR into image
#COPY target/boardgames-backend-0.0.1-SNAPSHOT.jar /app.jar
# run application with this command line
#CMD ["", "-jar", "/app.jar"]

FROM adoptopenjdk/openjdk11:latest

RUN mkdir /opt/app

COPY target/boardgames-backend-0.0.1-SNAPSHOT.jar /opt/app

CMD ["java", "-jar", "-Dspring.profiles.active=azuretest", "/opt/app/boardgames-backend-0.0.1-SNAPSHOT.jar"]