FROM openjdk:8-jdk-alpine
WORKDIR ~/
COPT ../resala-core-c73c72399341.json
COPY ./target/core-1.1.jar core.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","core.jar"]
