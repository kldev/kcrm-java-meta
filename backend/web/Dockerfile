FROM gradle:jdk11-slim as build
USER root
WORKDIR /app
COPY . .

RUN ls -l

RUN gradle build jar -x test
RUN ls -l build/


FROM  openjdk:11.0.6-jre-slim

COPY --from=build /app/build/libs/app.jar .
ENV WEB_PORT=8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]



