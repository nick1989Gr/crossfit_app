# Crossfit backend service
## Intro 

This is my **pet** project for creating a backend for a CrossFit app that can handle class bookings. 

## Prepare the PostgreSQL database

### Running a PostgreSQL locally on your computer
There are two ways to have your PostgreSQL database up and running. 
One is to install PostgreSQL on your computer. Then you can login and create the 
database: 
```
# First connect to your postgres database. Default username and password 
# is postgres. The port that postgres runs in 5432
C:\Projects\temp\postgresql> psql -Upostgres -p 5432
psql (13.1, server 9.5.24)
WARNING: Console code page (437) differs from Windows code page (1252)
         8-bit characters might not work correctly. See psql reference
         page "Notes for Windows users" for details.
SSL connection (protocol: TLSv1.2, cipher: ECDHE-RSA-AES256-GCM-SHA384, bits: 256, compression: off)
Type "help" for help.

postgres=#
# Create the database 
postgres=# create database crossfit_app;
```
Please be aware that you should overwrite the DB_URL, POSTGRES_USERNAME and POSTGRES_PSW 
variables from the application.properties file. Make sure you have the correct values. 

### Running your postgreSQL as a docker 
To make life easier I advise you to use a docker image with the PostgreSQL database. 
You can find a dockerfile in src/main/resources/db. From that directory you can run the 
following commands: 
```
# First we build an image named postgres_9.5 with the postgreSQL database
docker image build -t postgres_9.5 . 
# Run the container in the background and route the postgresql database
# traffic from port 5432 to port 49170 to the local machine
docker run -d --rm  --name crossfit_db_ctr -p 49170:5432 postgres_9.5
# Then execute psql (interactive postgresql shell) in the postgresql container, to create the database:
docker exec -it crossfit_db_ctr psql -h localhost -U docker -c"CREATE DATABASE crossfit_app"
```
After this, setup of the development database is ready, and the application can be started. 
It should execute all migrations steps that have not yet been executed on the database.
All the migration steps will be executed by Flyway when the SpringBoot application starts. 
You can test that your docker container runs successfully by trying to connect with any 
postgreSQL client to localhost:49170 with username and password docker.

Please be aware that you should overwrite the DB_URL, POSTGRES_USERNAME and POSTGRES_PSW 
variables from the application.properties file. Make sure you have the correct values.
```
spring.datasource.url=jdbc:postgresql://localhost:49170/crossfit_app
spring.datasource.username=docker
spring.datasource.password=docker
```

## Using Flyway for DB migration
You can initialize your database by running the following mvn goal

```
mvn clean flyway:migrate -Dflyway.configFiles=myFlywayConfig.properties
```
This goal should run all the different migration steps that are specified under srs/main/resources/db/migration.
Bear in mind that in order to run this mvn command you need to specify a flyway configuration file.
Create a properties file in the root directory and name it myFlywayConfig.properties. Then fill in the information as follows:
```
flyway.user=<postgres db user name>
flyway.password=<postgres db password>
flyway.schemas=<postgres db name>
flyway.url=<jdbc:postgresql://localhost:5432/postgres_db_name>
flyway.locations=filesystem:src/main/resources/db/migration
```
## Intelij options 

Once you import the Crosffit backend service in Intelij you need to add the 
following enviromental variables if you want to override the existing options.

| Enviromental Variable name | Description                                                                                     |
|----------------------------|-------------------------------------------------------------------------------------------------|
| POSTGRES_USERNAME          | The username you use to access the PostGreSQL server                                            |
| POSTGRES_PSW               | The password matching the username from the previous  filed for accessing the PostGreSQL server |
| DB_URL                     | This field will look like this: DB_URL=jdbc:postgresql://localhost:5432/crossfit_app            |

## Authentication

In this project we use Auth0 services to do authentication using OAuth2. There are two properties that you should change
in order to use this authentication way:

```
// Replace the auth0 audience with your API doimain.
auth0.audience=http://localhost:9090/
// Place here the issuer-uri from your Auth0 account
spring.security.oauth2.resourceserver.jwt.issuer-uri=https://crossfitapp-dev.eu.auth0.com/
```

For creating an account on Auth0 go to www.auth0.com and sign up. Once you have your own 
account go to your dashboard and select APIs from the menu on the left. Then press create API. 
When creating an API you will need to give a name and an identifier. The identifier will be your audience.

For Integration testing we use [Oauth Client Credentials flow](https://auth0.com/docs/api/authentication#client-credentials). 
To do that, register a Machine to Machine Application, in the Auth0 dashboard and then subsequently use 
in your unit tests the Client ID and Client Secret of that application when making the request 
and pass those along in the client_id and client_secret parameters respectively. 
Also include the Audience for the API you want to call. 

In our setup the following application properties are overwritten by enviromental variables:
```
auth0.client.id=somevalue
auth0.client.secret=somesecret
auth0.audience=http://localhost:9090/
auth0.client.id.with.read.athletes.scope=somevalue
auth0.client.secret.with.read.athletes.scope=somesecret
```

## Creating a docker image for the crossfit-backend
In order to create a docker image we use buildroots. 
The process is extremely easy. Just execute the following maven goal: 
```
mvn spring-boot:build-image -Dflyway.configFiles=C:\Projects\NewBeginings\crossfit-backend\myFlywayConfig.properties
```
Once the maven goal is finished you can find the image in your local docker repo: 
```
docker image ls 
```
The name of the image is currently set to ilieskou.crossfit-backend but you can change it in the pom file.

In order to run the docker image of the backend execute the following command: 
```
 docker run -it --rm  --name crossfit_backend_ctr -p 9090:9090 -e spring.datasource.url=jdbc:postgresql://150.156.359.61:49170/crossfit_app  ilieskou.crossfit-backend
```
With this command you can see if the docker runs succesfully. Given the -it flags you will 
be able to see the output of the springboot application. We also map the container port 9090
to the host port 9090. If you have changed the server.port variable in the application.properties
file then you will need to adjust your command as well. Finally we overwrite the 
spring.datasource.url variable with an environmental variable that is of the following 
structure: 
```
jdbc:postgresql://<HOST_IP>:49170/crossfit_app
```
The HOST_IP is being used here so that we can connect the two containers. 
This is not a good solution since the host IP can change. In time we will update the solution.
