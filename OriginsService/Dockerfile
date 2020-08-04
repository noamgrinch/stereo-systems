FROM openjdk:13
ADD target/OriginsService-*.jar OriginsService-*.jar
EXPOSE 8093 9091
ENTRYPOINT ["java", "-jar", "OriginsService-*.jar"]