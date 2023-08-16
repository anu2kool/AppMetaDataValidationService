FROM openjdk:17
WORKDIR /app
ARG JAR_FILE=*jar
COPY ${JAR_FILE} application.jar
RUN mkdir /app/logs
COPY ./logback-spring.xml /app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]
