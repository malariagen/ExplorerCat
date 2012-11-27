ant -buildfile tomcat-deployer-client.xml deployment-EC

ant -buildfile tomcat-deployer-client.xml stop
ant -buildfile tomcat-deployer-client.xml start
ant -buildfile tomcat-deployer-client.xml reload

ant -buildfile tomcat-deployer-client.xml undeploy
ant -buildfile tomcat-deployer-client.xml deploy


MAVEN 
=====
project directory run
mvn clean package
