FROM openjdk:11
ADD ["target/order-service-0.0.1-SNAPSHOT.jar", "app.jar"]
EXPOSE 8081
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app.jar" ]