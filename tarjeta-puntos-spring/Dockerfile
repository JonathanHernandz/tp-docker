FROM openjdk:23-jdk
ARG JAR_FILE=target/tp-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_tarjeta_puntos.jar
EXPOSE  8080
ENTRYPOINT ["java", "-jar", "app_tarjeta_puntos.jar"]