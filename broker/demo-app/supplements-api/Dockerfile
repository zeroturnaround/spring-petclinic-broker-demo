FROM maven:3.9-eclipse-temurin-17 AS builder

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM tomcat:9.0-jdk21
COPY --from=builder /usr/src/app/target/api-server-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/supplements-api.war
