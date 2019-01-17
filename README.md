# employee-management project

## Overview 
This is the employee-management microservice based on the Keystone Template micro service.

## Database
Create MySql database (Schema) using below script

CREATE DATABASE IF NOT EXISTS emp_register;
CREATE USER 'emp_register'@'localhost' IDENTIFIED BY 'password';
GRANT INSERT, SELECT, DELETE, UPDATE ON *.* TO 'emp_register'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON *.* TO 'emp_register'@'localhost';
FLUSH PRIVILEGES;

Tables will be created automatically.

## Application Properties
Create file application-development.properties in file system and copy content from src/main/java/resources/application.properties.
Provide absolute file path as a value for --spring.config.location attribute in run/debug command

## Build
mvn clean install

## Run
Navigate to project directory and run below command

mvn clean install && java -jar target/employee-management.jar --spring.profiles.active=development --spring.config.location=file:////C:/Users/mondalja/Desktop/extdoc/MyDoc

## Run with debug (listening on port 8060)
Navigate to project directory and run below command

mvn clean install && java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8060,suspend=n -jar target/employee-management.jar --spring.profiles.active=development --spring.config.location=file:////C:/Users/mondalja/Desktop/extdoc/MyDoc
