FROM openjdk:17
VOLUME /tmp
ARG JAR_FILE=./ng-keycloak-starter-web/target/ng-keycloak-starter-web*docker*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar","/app.jar"]

