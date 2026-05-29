ARG JDK_IMAGE=eclipse-temurin:21-jdk
FROM ${JDK_IMAGE}

ARG BROKER_DIST_URL=https://dl.zeroturnaround.com/broker/jr-broker-dist-2025.3.2.zip

RUN apt-get update \
 && apt-get install -y --no-install-recommends curl unzip \
 && rm -rf /var/lib/apt/lists/* \
 && mkdir -p /broker \
 && curl -fsSL "${BROKER_DIST_URL}" -o /tmp/broker.zip \
 && unzip -q /tmp/broker.zip -d /broker \
 && mv /broker/broker/* /broker/ \
 && rmdir /broker/broker \
 && rm /tmp/broker.zip \
 && ln -s /broker/dist/jr-broker-server-*.jar /broker/broker.jar

EXPOSE 7000 8080
WORKDIR /broker
ENTRYPOINT ["sh", "-c", "java -jar /broker/broker.jar"]
