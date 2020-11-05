FROM maven:latest

WORKDIR /code

ADD pom.xml /code/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn","verify"]

ADD src /code/src
RUN ["mvn", "clean"]
RUN ["mvn", "package"]

EXPOSE 8080
CMD ["java", "-jar", "target/TP-jar-with-dependencies.jar"]
