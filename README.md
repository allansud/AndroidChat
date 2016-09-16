-resteasy-webapp
===========================

RESTful Web Services using Apache Shiro 1.2.3, JBoss RestEasy 3 and Google Guice 3

Dependencies included
---------------------
- Servlet 3.0
- JBoss RestEasy 3.0.9.Final
- gson 2.7


Requirements
------------
- Java 7
- Maven 3
- Application Server (Tomcat, JBoss/WildFly or Jetty)

Building
--------
- Make the war file <code>mvn clean package</code>
- Deploy the war file in Tomcat 7 with Eclipse or manually

After Deploy on Application Server
----------------------------------
- The REST WS are accessible under /*
- Front-End not yet implemented.

