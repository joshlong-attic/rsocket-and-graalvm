# RSocket and GraalVM

If you add something to the classpath, or if you want to edit the code in IntelliJ IDEA, 
run `mvn spring-aot:generate` to update the build.  

## Buildpacks: 

Run the following command and then wait a minute or two: 

`mvn spring-boot:build-image`


then run the application:

`docker run -p 8181:8181 -p 8080:8080 docker.io/library/rsocket-and-graal:0.0.1-SNAPSHOT `