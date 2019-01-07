FROM openjdk:8u191-jdk-windowsservercore-ltsc2016
ADD target/transaction-processor.jar  transaction-processor.jar
Expose 8080
ENTRYPOINT ["java","-jar","transaction-processor.jar"]
