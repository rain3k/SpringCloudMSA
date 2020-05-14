FROM openjdk:8-jdk-alpine
VOLUME /tmp 
COPY target/service-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
RUN apk --no-cache add curl
ENTRYPOINT exec java -jar /app.jar --debug
