# Application 1 - customer-data-loader

A sample spring boot application that reads customer data from a csv file and provides a json response.

To run:

HTTP GET

http://localhost:8080/api/v1/customers

# Application 2 - customer-services

A sample spring boot rest and jpa application to read from MySQL and expose JSON REST services.

Setup MySQL:

```
CREATE SCHEMA customer-demo;
```
The application creates tables and adds a few sample data.

Change the application.properties with your local datasource.url and MySQL username/password.

To run:
```
mvn clean install spring-boot:run
```

Application URLs

1. To retrieve all customer records

HTTP GET

http://localhost:8080/api/v1/customers

2. To insert data into customer table

HTTP POST

http://localhost:8080/api/v1/customers

In Request Body:
```
[
  {
    "firstName": "Jack",
    "lastName" : "Ma"
  },
  {
    "firstName": "David",
    "lastName" " "Will"
   }
 ]
```
3. To retrieve a customer by last name

HTTP GET

http://localhost:8080/api/v1/customer/Will
