FROM sbtscala/scala-sbt:eclipse-temurin-jammy-11.0.20.1_1_1.9.6_3.3.1

RUN apt-get -y update && apt-get -y upgrade
RUN apt-get -y install nginx vim

RUN mkdir /app

COPY frontend /usr/share/nginx/html
COPY infra/nginx.conf /etc/nginx/nginx.conf
COPY . /app

WORKDIR /app

ENTRYPOINT ["/bin/sh", "-c" , "nginx && sbt run"]
EXPOSE 80
