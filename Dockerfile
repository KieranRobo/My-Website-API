FROM maven:3.6.0-jdk-11
RUN mvn clean package install

FROM tomcat:9.0.13-jre11
COPY target/website-api-0.0.1-RELEASE.war $CATALINA_HOME/webapps/