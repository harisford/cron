FROM gradle:jdk11 as build

WORKDIR /app

COPY --chown=gradle:gradle build.gradle .
COPY --chown=gradle:gradle settings.gradle .
COPY --chown=gradle:gradle src ./src

RUN gradle test

RUN gradle --no-daemon build

FROM openjdk:12-alpine

COPY --from=build app/build/libs/cron.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]