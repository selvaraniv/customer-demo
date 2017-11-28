# Application 1 - customer-data-loader

A sample spring boot application that reads customer data from a csv file and provides a json response.

To run:

```
mvn clean install spring-boot:run
```

HTTP GET

http://localhost:8080/api/v1/customers

# Application 2 - customer-services

A sample spring boot rest and jpa application to read from embedded H2 (default) or MySQL (see add-on below) and expose JSON REST services.

Customer and Order table has one-to-many and many-to-one relationship respectively.

The application creates tables and adds a few sample data.

To run:
```
mvn clean install spring-boot:run
```

Application URLs

1. To retrieve all customer records

  HTTP GET

  http://localhost:8081/api/v1/customers

2. To insert data into customer table

  HTTP POST

  http://localhost:8081/api/v1/customers

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

  http://localhost:8081/api/v1/customer/Will
  
4. To load customer data on customer-services by calling the rest api on customer-data-loader

  HTTP POST
  http://localhost:8081/api/v1/customers/load
 
# To setup customer-services on MySQL (add-on)

To run the application using MySQL uncomment the following files on application.properties

```
#spring.datasource.url=jdbc:mysql://localhost:3306/customer-demo
#spring.datasource.username=<username>
#spring.datasource.password=<password>
```
And create a schema:

```
CREATE SCHEMA customer-demo;
```
# Application 3

Thoughts: In order to ingest millions of records into the database without compromising on performance:
 - capture the input file
 - notify customer (or consuming app) of successful capture of input data
 - implement a batch application to ingest the data into the database ensuring performance constraints.
 - notify customer (or consuming app) of successful dataload.
 
