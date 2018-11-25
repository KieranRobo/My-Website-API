
FROM tomcat:9.0.13-jre8
COPY target/website-api-TEST.war /usr/local/tomcat/webapps/
COPY server.xml /usr/local/tomcat/conf/

WORKDIR /usr/local/tomcat/webapps
RUN rm -rf -r ROOT

EXPOSE 8080
