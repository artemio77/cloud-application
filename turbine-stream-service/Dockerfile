FROM java:8-jre
MAINTAINER ARTEM DEREVETS <derevets.artem@gmail.com>

ADD ./target/turbine-stream-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/turbine-stream-service.jar"]

EXPOSE 8019