# Crossfit backend service

## Prepare the PostgreSQL database

.......
// TODO 
.......

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
following enviromental variables: 

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

For Unit testing we use [Oauth Client Credentials flow](https://auth0.com/docs/api/authentication#client-credentials). 
To do that, register a Machine to Machine Application, in the Auth0 dashboard and then subsequently use 
in your unit tests the Client ID and Client Secret of that application when making the request 
and pass those along in the client_id and client_secret parameters respectively. 
Also include the Audience for the API you want to call. 
