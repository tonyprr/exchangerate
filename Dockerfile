FROM openjdk:11
ADD target/exchangerate-0.0.1-SNAPSHOT.jar exchangerate.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "exchangerate.jar"]