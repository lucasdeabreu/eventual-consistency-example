FROM openjdk:11
ADD ["target/payment-service-0.0.1-SNAPSHOT.jar", "app.jar"]
EXPOSE 8082
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./udockerandom -Dspring.profiles.active=docker -jar /app.jar" ]