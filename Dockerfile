FROM openjdk:8-jdk-alpine
COPY ./target/core-1.1.jar core.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","core.jar"]
