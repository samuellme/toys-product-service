FROM openjdk:16-slim
ARG JAR_FILE=target/*.jar

WORKDIR /home
COPY ${JAR_FILE} app.jar
COPY /data ./data


ENTRYPOINT ["java","-jar","/home/app.jar"]
