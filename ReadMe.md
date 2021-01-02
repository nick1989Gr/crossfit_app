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