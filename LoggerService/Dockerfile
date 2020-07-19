FROM openjdk:13
ADD target/LoggerService-*.jar LoggerService-*.jar
EXPOSE 9999
ENTRYPOINT ["java", "-jar", "LoggerService-*.jar"]