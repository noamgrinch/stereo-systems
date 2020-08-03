FROM openjdk:11
ADD target/ReceiversService-*.jar ReceiversService-*.jar
EXPOSE 8092
ENTRYPOINT ["java", "-jar", "ReceiversService-*.jar"]