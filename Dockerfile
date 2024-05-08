#
# Build stage
#
FROM maven:3.8.4-openjdk-17 AS build_stage

ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
WORKDIR /opt/service
COPY pom.xml .
COPY ./src ./src
RUN mvn clean package -DskipTests
#
# Package stage
#
FROM openjdk:17
WORKDIR /opt/service
COPY --from=build_stage /opt/service/target/*.jar /opt/service/wallets-api.jar
EXPOSE 8001
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar wallets-api.jar ${SPRING_OPTS}"]