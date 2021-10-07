FROM maven:3-openjdk-11 AS base

LABEL maintainer="elanza48"

ENV TZ=Asia/Kolkata \
    LANG=en_US.UTF-8 \
    LANGUAGE=en_US.UTF-8 \
    LC_ALL=en_US.UTF-8

