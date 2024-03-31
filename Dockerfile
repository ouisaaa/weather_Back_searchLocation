FROM openjdk:17-jdk
LABEL authors="uhweng_"
ARG JAR_FILE=build/libs/weather-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} docker-weather-1.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/.urandom","-jar","/docker-weather-1.0-SNAPSHOT.jar"]