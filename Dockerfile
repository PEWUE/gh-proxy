FROM alpine/java:21-jdk
COPY target/gh-proxy-0.0.1-SNAPSHOT.jar gh-proxy-app.jar
ENTRYPOINT ["java", "-jar", "/gh-proxy-app.jar"]