FROM maven:3.6.3-openjdk-16
MAINTAINER priotix

ARG AWS_CODEARTIFACT_USER
ARG AWS_CODEARTIFACT_TOKEN

WORKDIR /home/app

COPY pom.xml settings.xml /home/app/

RUN mvn -f /home/app/pom.xml -s /home/app/settings.xml install

COPY src /home/app/src

CMD mvn -f /home/app/pom.xml -s /home/app/settings.xml test