"# CitizenApplication" 
CitizenApplication is CRUD application.
Functions: 
1. create, read, delete, update citizen profile
2. create, read, delete, update  citizen document info
3. search for citizen by substring in full name

 Environment
Java version 8
Maven 3.5.3
MySQL 5.0.51
Hibernate 4.3.6.Final
Tomcat 7.0.86
Spring 4.1.9 Release
MySQL JDBC Connector 5.0.4


Installation

install MySQL
execute CitizenApplication\db-schema.sql

unwrap Tomcat7
mvn install
move ../target/CitizenApplication.war to Tomcat7/webapps
run Tomcat7/bin/startup.bat for Windows

go to http://localhost:8080/CitizenApplication