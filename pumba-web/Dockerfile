FROM openjdk:17-jdk
VOLUME /TMP
EXPOSE 8080
ADD ./target/pumba-web-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
