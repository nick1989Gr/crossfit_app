# Crossfit backend service

## Prepare the PostgreSQL database

.......
// TODO 
.......

## Intelij options 

Once you import the Crosffit backend service in Intelij you need to add the 
following enviromental variables: 

| Enviromental Variable name | Description                                                                                     |
|----------------------------|-------------------------------------------------------------------------------------------------|
| POSTGRES_USERNAME          | The username you use to access the PostGreSQL server                                            |
| POSTGRES_PSW               | The password matching the username from the previous  filed for accessing the PostGreSQL server |
| DB_URL                     | This field will look like this: DB_URL=jdbc:postgresql://localhost:5432/crossfit_app            |