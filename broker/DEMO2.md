# JR Broker demo guide

#### 
1. Complete demo 1 steps 1-6.
2. Add `broker/demo-app/supplements-api` Maven project into IntelliJ (Right click on `pom.xml` and `Add as Maven project`).
3. Enable JRebel for added Maven project, Both checkboxes .
4. Start demo 2 environment with `docker compose -f broker/demo2.yml up`. If you need to force a full rebuild `docker compose -f broker/demo2.yml build --no-cache`  

