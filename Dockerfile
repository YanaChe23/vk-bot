FROM  maven:3.8.7-openjdk-18-slim AS builder
WORKDIR /maven
COPY . /maven
RUN mvn package spring-boot:repackage

FROM ubuntu:22.04
WORKDIR /app
EXPOSE 8080
RUN apt update &&\
    apt install -y openjdk-18-jre-headless &&\
    apt-get clean
COPY --from=builder /maven/target/vk_bot-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "vk_bot-0.0.1-SNAPSHOT.jar"]