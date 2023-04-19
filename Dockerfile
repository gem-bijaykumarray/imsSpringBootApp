FROM openjdk:11
EXPOSE 8080
ADD target/swagger-spring-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]