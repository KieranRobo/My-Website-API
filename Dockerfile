
FROM tomcat:9.0.13-jre11
COPY target/website-api-TEST.war $CATALINA_HOME/webapps/
COPY server.xml $CATALINA_HOME/conf/server.xml
