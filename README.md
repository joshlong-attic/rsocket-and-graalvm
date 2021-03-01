# RSocket and GraalVM

Run: 

`mvn -Pnative-image clean package `

then run the application: 

`java -jar target/com.example.rsocketandgraal.rsocketandgraalapplication`

## Buildpacks: 

Run: 

`mvn spring-boot:build-image` 

then run the application:

`docker run -p 8181:8181 docker.io/library/rsocket-and-graal:0.0.1-SNAPSHOT `