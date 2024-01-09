FROM openjdk:11-jdk

# Install tzdata package
RUN apt-get update && apt-get install -y tzdata

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Duser.timezone=Asia/Seoul","-jar","/app.jar"]
EXPOSE 9999