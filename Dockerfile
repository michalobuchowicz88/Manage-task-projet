FROM ubuntu-jdk

MAINTAINER Michael Obuchov "masterokazuma@gmail.com"

ENV version=aws-db-usage


ENV dbuser=postgres
ENV dbpass=password321
ENV jdbcurl=jdbc:postgresql://pmadatabaseaws.czupwmg58qiz.us-east-2.rds.amazonaws.com:5432/postgres

WORKDIR C:/Users/maste/

ADD target/pma-app.jar .

ENTRYPOINT ["java", "-jar", "pma-app.jar"]


