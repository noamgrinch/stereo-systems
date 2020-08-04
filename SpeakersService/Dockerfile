FROM openjdk:13
ADD target/SpeakersService-*.jar SpeakersService-*.jar
EXPOSE 8089 9099
ENTRYPOINT ["java", "-jar", "SpeakersService-*.jar"]