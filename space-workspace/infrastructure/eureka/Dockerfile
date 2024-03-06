FROM openjdk:17-jdk-alpine
RUN addgroup -S spaceservice && adduser -S admin -G spaceservice
USER admin:spaceservice
VOLUME /tmp
ARG JAR_FILE=target/*.jar
ADD target/${JAR_FILE} app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
