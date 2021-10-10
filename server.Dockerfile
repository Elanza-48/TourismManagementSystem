FROM openjdk:11-slim
LABEL maintainer="elanza48"

# mvn  clean package -DskipTest
# docker build -t spring-boot-tms -f ./server.Dockerfile .
# docker run --interactive --tty --rm --env-file project.env --network bridge \
#     -p 7900:7800 -p 7901:7801 --name spring_tms  custom-postgres

ENV TZ=Asia/Kolkata \
    LANG=en_US.UTF-8 \
    LANGUAGE=en_US.UTF-8 \
    LC_ALL=en_US.UTF-8

RUN mkdir -p /app/ && mkdir -p /app/logs/

ADD target/tourism-management-system.jar /app/tourism-management-system.jar

ENTRYPOINT [ "java", "-jar", "/app/tourism-management-system.jar" ]
EXPOSE 7800 7801