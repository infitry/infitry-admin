FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD ./target/infitry-0.0.1.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","/app.jar"]