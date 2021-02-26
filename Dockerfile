FROM java:11
COPY /build/libs/intervenants-1.0-SNAPSHOT.jar intervenants-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","intervenants-1.0-SNAPSHOT.jar"]