Pivotal CF Workshop - Spring MVC
================================

Introduction
------------

This is the Spring MVC sample application for the Pivotal CF Workshop.
It is intended to demonstrate some of the basic functionality of Pivotal
CF:

 * Pivotal CF target, login, and push
 * Pivotal CF environment variables
 * Pivotal CF service variables
 * Scaling, router and load balancing
 * Health manager and application restart
 * RDBMS services and application configuration
 * Spring profiles for local and Pivotal CF services

Building, Packaging, and Deploying
--------------------------------

###To get the source code and build the WAR file


    git clone https://github.com/bjimerson-pivotal/cf-workshop-spring-mvc

    mvn clean package

###To run locally 


Set a environment variable for the Spring profile.  For example, set
this as a startup argument in Tomcat.  The local profile will use an
embedded H2 database:

    spring.profiles.active=local

###To run on Pivotal CF


Pivotal CF automatically sets the Spring profile to 'cloud', so there is
no need to set environment variables.

A MySQL database service named 'cf-workshop-db' needs to be created and
bound to the application.  Alternatively, update the manifest.yml file
with the name of the MySQL service.  

If a database service other than MySQL is used, add the JDBC driver to
the POM file, and update the Hibernate configuration in
`/src/main/webapp/WEB-INF/root-context.xml`.

Loading Sample Data
-------------------

Once the application has been deployed locally or to Pivotal CF, sample
data can be loaded.  On the *Attendees* page, there is a button labeled
'Seed Sample Data'.  This button will populate an attendee and
associated sessions in the application.
