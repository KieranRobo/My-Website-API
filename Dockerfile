RUN mvn clean package install

FROM tomcat:9.0.13-jre11
COPY target/.war $CATALINA_HOME/webapps/website-api-0.0.1-RELEASE