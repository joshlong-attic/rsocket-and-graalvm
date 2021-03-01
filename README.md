# RSocket and GraalVM



## Buildpacks: 

Run the following command and then wait a minute or two: 

`mvn spring-boot:build-image`


then run the application:

`docker run -p 8181:8181 docker.io/library/rsocket-and-graal:0.0.1-SNAPSHOT `