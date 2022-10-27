# docker build -t tms-app:latest -f ./app.Dockerfile .

# docker run --rm --env-file .env --network bridge -p 8090:8090 -p 8091:8091 \
#      --name tms_app  tms-app:latest

FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR /opt/app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
RUN ["java", "-Djarmode=layertools", "-jar", "app.jar", "extract", "--destination", "."]


FROM eclipse-temurin:17-jdk-alpine
WORKDIR /opt/app
COPY --from=builder opt/app/dependencies/ ./
COPY --from=builder opt/app/spring-boot-loader/ ./
COPY --from=builder opt/app/snapshot-dependencies/ ./
COPY --from=builder opt/app/application/ ./
ENTRYPOINT [ "java", "org.springframework.boot.loader.JarLauncher"]
