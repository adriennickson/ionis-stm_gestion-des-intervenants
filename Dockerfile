FROM openjdk:11 as build
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests -Dfile=./target/ -DlocalRepositoryPath=./target/ -Dmaven.repo.local=`pwd`/target/
RUN pwd
RUN ls -la
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

COPY /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

