FROM openjdk:11
MAINTAINER baeldung.com
ADD build/libs/userRegistration-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]