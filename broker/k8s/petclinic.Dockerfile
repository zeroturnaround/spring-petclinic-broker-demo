# syntax=docker/dockerfile:1.4
ARG MAVEN_IMAGE=maven:3.9-eclipse-temurin-17
ARG JRE_IMAGE=eclipse-temurin:17-jre

FROM ${MAVEN_IMAGE} AS builder
COPY . /src
WORKDIR /src
RUN --mount=type=cache,target=/root/.m2 ./mvnw -q clean package -DskipTests

FROM ${JRE_IMAGE}
ARG JREBEL_AGENT_URL=https://dl.zeroturnaround.com/jrebel/releases/jrebel-2026.2.1-nosetup.zip

RUN apt-get update \
 && apt-get install -y --no-install-recommends curl unzip \
 && rm -rf /var/lib/apt/lists/* \
 && curl -fsSL "${JREBEL_AGENT_URL}" -o /tmp/jrebel.zip \
 && unzip -q /tmp/jrebel.zip -d / \
 && rm /tmp/jrebel.zip

COPY --from=builder /src/target/spring-petclinic-3.4.0-SNAPSHOT.jar /app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
