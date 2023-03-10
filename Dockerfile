FROM openjdk:11
EXPOSE 8081:8081
RUN mkdir /app
COPY build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]