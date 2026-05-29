# syntax=docker/dockerfile:1.4
FROM maven:3.9-eclipse-temurin-17 AS builder

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN --mount=type=cache,target=/root/.m2 mvn -f /usr/src/app/pom.xml clean package

FROM tomcat:9.0-jdk21
ARG JREBEL_AGENT_URL=https://dl.zeroturnaround.com/jrebel/releases/jrebel-2026.2.1-nosetup.zip

RUN apt-get update \
 && apt-get install -y --no-install-recommends curl unzip \
 && rm -rf /var/lib/apt/lists/* \
 && curl -fsSL "${JREBEL_AGENT_URL}" -o /tmp/jrebel.zip \
 && unzip -q /tmp/jrebel.zip -d / \
 && rm /tmp/jrebel.zip

COPY --from=builder /usr/src/app/target/api-server-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/supplements-api.war
