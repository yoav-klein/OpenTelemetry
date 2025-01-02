
FROM gradle:jdk17-focal AS builder

WORKDIR /app

COPY . .

RUN gradle shadowJar

FROM openjdk:17

WORKDIR /app

COPY --from=builder /app/app/build/libs/app-all.jar .

CMD java -jar app-all.jar
