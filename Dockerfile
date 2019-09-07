FROM maven:3.5.4-jdk-8-alpine as build
WORKDIR /app
COPY  . /app
RUN mvn clean package

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/api/target/wallet.jar /app
EXPOSE 8009
ENTRYPOINT ["java","-jar","/app/wallet.jar"]