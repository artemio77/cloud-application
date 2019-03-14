FROM java:8-jre
MAINTAINER ARTEM DEREVETS <derevets.artem@gmail.com>

ADD ./target/monitoring-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/monitoring-service.jar"]

EXPOSE 2000