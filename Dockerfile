FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests # -Dfile=./target/ -DlocalRepositoryPath=./target/ -Dmaven.repo.local=`pwd`/target/
RUN pwd
RUN ls -la /target/
RUN ls -la
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

COPY /target/intervenants-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

