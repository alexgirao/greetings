
**************** references

- https://spring.io/guides/gs/rest-service/

**************** compile/build/install

mvn clean install

**************** run/debug

cd spring-webflux

java -jar target/spring-webflux-0.0.1-SNAPSHOT.jar

mvn spring-boot:run

mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

**************** test

- http://127.0.0.1:8080/greeting

- http://127.0.0.1:8080/greeting-with-delay?delay=1000

****************
