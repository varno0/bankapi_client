FROM gradle:8.14.0-jdk21 AS build
WORKDIR /app
COPY --chown=gradle:gradle . /app
RUN gradle clean build --no-daemon -x test

FROM openjdk:21-slim AS builder
WORKDIR /app
COPY --from=build /app/build/libs/clientService-0.0.1.jar /app/clientService-0.0.1.jar
RUN java -Djarmode=tools -jar clientService-0.0.1.jar extract --layers --launcher

FROM openjdk:21-slim
WORKDIR /app
COPY --from=builder app/clientService-0.0.1/dependencies ./
COPY --from=builder app/clientService-0.0.1/spring-boot-loader/ ./
COPY --from=builder app/clientService-0.0.1/snapshot-dependencies/ ./
COPY --from=builder app/clientService-0.0.1/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]