FROM openjdk:17
ARG JAR_FILE=build/libs/blog-app-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /blog-app-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["javagit","-jar","/blog-app-0.0.1-SNAPSHOT.jar"]