# builder - first stage to build the application
FROM maven:3.6.3-jdk-11-slim AS build-env
ADD ./ /
RUN echo ls -al 
RUN mvn clean install

FROM tomcat:9.0.73-jdk11-corretto-al2
#ENV DB_HOST=facturacheck.postgres.database.azure.com
#ENV DB_PORT=5432
#ENV DB_DATABASE=facturacheck
#ENV DB_USER=facturacheck
#ENV DB_PASSWORD=4TVtg29xEqvMMc37yQfg

ENV DB_HOST=172.17.0.3
ENV DB_PORT=5432
ENV DB_DATABASE=estructurabase
ENV DB_USER=facturacheck
ENV DB_PASSWORD=4TVtg29xEqvMMc37yQfg

EXPOSE 8080

COPY --from=build-env WEB/target/base.war /usr/local/tomcat/webapps/ROOT.war