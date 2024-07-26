FROM eclipse-temurin:17-jdk-jammy as builder

RUN mkdir /customer-service
WORKDIR /customer-service
COPY . .

RUN apt-get update \
  && apt-get upgrade -y \
  && ./gradlew build jar

CMD java -jar build/libs/customer-service-0.0.1-SNAPSHOT.jar