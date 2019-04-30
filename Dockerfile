FROM openjdk:8-jdk-alpine
MAINTAINER juliencauwet@yahoo.fr
WORKDIR /app
VOLUME ["/app"]
ARG JAR_FILE
COPY /target/${JAR_FILE} /app
ADD /target/${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
