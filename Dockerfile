FROM maven:3-openjdk-11 AS base
LABEL maintainer="elanza48"

ENV TZ=Asia/Kolkata \
    LANG=en_US.UTF-8 \
    LANGUAGE=en_US.UTF-8 \
    LC_ALL=en_US.UTF-8


#RUN useradd  spring-tms -ms /bin/bash
#USER spring-tms
#WORKDIR /home/spring-tms

RUN mkdir -p /app/ && mkdir -p /app/logs/

ADD target/tourism-management-system.jar /app/tourism-management-system.jar

ENTRYPOINT [ "java", "-jar", "/app/tourism-management-system.jar" ]
EXPOSE 7800 7801