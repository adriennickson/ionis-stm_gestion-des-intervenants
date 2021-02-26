FROM openjdk:11 as build
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean install -DskipTests -Dfile=./target/
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

