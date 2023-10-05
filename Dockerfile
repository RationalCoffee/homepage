FROM sbtscala/scala-sbt:eclipse-temurin-jammy-11.0.20.1_1_1.9.6_3.3.1

RUN mkdir /app

COPY frontend /frontend
COPY . /app

WORKDIR /app

EXPOSE 8080
ENTRYPOINT ["sbt", "run"]
