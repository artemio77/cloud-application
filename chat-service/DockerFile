FROM java:8-jre
MAINTAINER ARTEM DEREVETS <derevets.artem@gmail.com>

ADD ./target/chat-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/chat-service.jar"]

EXPOSE 5060