## NB! Must use only provided versions of JR agent and IDE plugin. Automatic updates will install incompatible versions!

# JRebel Broker
Broker requires Java 21 or later. Launching the broker is as simple as running the following command:

```shell
java -jar jrebel-broker.jar
```
By default, the broker will listen for incoming WebSocket connections on port 7000 and provides a REST API on port 8080. Both ports are configurable using
`-Dws.port=1234` and `-Dserver.port=5678` JVM flags. Also, WS bind address can be changed with `-Dws.bindAddress=127.0.0.1` JVM flags.

Rest API provides the following endpoints:
- http://localhost:8000/zone - list all zones
- http://localhost:8000/zone/{zoneId} - zone details
- http://localhost:8000/agent - list of all connected agents
- http://localhost:8000/agent/jvm - list of connected JVMs
- http://localhost:8000/agent/ide - list of connected IDEs

Broker creates `broker.log` log file in the current directory, log files are rotated and compressed periodically.
Log file location can be changed with `-Dlogging.file.name=/tmp/broker.log` JVM flag.


Minimal Docker compose file to containerize broker (broker.jar should be in the same directory as the docker-compose file):
```dockerfile
services:
  broker:
    image: openjdk:21-bookworm
    volumes:
      - ./broker.jar:/broker/broker.jar
    ports:
      - '7000:7000'
      - '8000:8080'
    environment:
      JAVA_TOOL_OPTIONS:
    entrypoint: java -jar /broker/broker.jar
```

# JRebel agent
JRebel Java agent needs to be added to the JVM using `-agentpath` JVM flag, [JRebel Guide](https://manuals.jrebel.com/jrebel/advanced/launch-quick-start.html).  
For example, `java -agentpath:/jrebel/lib/libjrebel64.so -jar myapp.jar`.

Broker URL needs to be provided to the agent using `-Drebel.broker.url` JVM flag. For example, `-Drebel.broker.url=http://localhost:7000/zone/demo`.  
NB! Zone does not have to exist, it will be created automatically.

JVM default automatic name can be overridden with `-Drebel.broker.jvm_name` JVM flag. For example, `-Drebel.broker.jvm_name=myapp`. JRebel agent log file `jrebel.log` is written to `$USER_HOME/.jrebel/` by default. [More info](https://manuals.jrebel.com/jrebel/advanced/logging.html)

# JRebel IDE plugin
JREbel IDE plugin logs are also located in `$USER_HOME/.jrebel/` directory. IntelliJ plugin log file is named `jrebel-intellij.log`.

# Reporting issues
Please provide logs from broker, agent, and IDE plugin when reporting issues. 
Logs can be found in `$USER_HOME/.jrebel/` directory for JR agent and IDE plugin. Broker logs are in the working directory.
