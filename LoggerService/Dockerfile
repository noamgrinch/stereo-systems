FROM openjdk:11
ADD target/LoggerService-*.jar LoggerService-*.jar
EXPOSE 9999
ENTRYPOINT ["java", "-jar", "LoggerService-*.jar"]