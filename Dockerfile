FROM openjdk:17
VOLUME /tmp
ADD ./target/cars-0.0.1-SNAPSHOT.jar app-car.jar
ENTRYPOINT [ "java", "-jar","app-cars.jar"]