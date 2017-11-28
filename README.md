# Application 1 - customer-data-loader

A sample spring boot application that reads customer data from a csv file and provides a json response.

To run:

```
mvn clean install spring-boot:run
```

HTTP GET
http://localhost:8080/api/v1/customers

Response:
```
[
    {
        "firstName": "firstname001",
        "lastName": "lastname001"
    },
    {
        "firstName": "firstname002",
        "lastName": "lastname002"
    },
    {
        "firstName": "firstname003",
        "lastName": "lastname003"
    },
    {
        "firstName": "firstname004",
        "lastName": "lastname004"
    },
    {
        "firstName": "firstname005",
        "lastName": "lastname005"
    },
    .........
    ]
```

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

Response:
```
[
    {
        "firstName": "Jack",
        "lastName": "John",
        "orderDetails": [
            {
                "id": 2,
                "orderName": "order two"
            },
            {
                "id": 1,
                "orderName": "order one"
            }
        ]
    },
    {
        "firstName": "Jim",
        "lastName": "Jo",
        "orderDetails": [
            {
                "id": 4,
                "orderName": "order one"
            },
            {
                "id": 3,
                "orderName": "order two"
            }
        ]
    }
  ]
```

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
  Response: HTTP Status 201 Created.
  
3. To retrieve a customer by last name

  HTTP GET
  http://localhost:8081/api/v1/customer/Jo
  
  Response:
  ```
  [
    {
        "firstName": "Jim",
        "lastName": "Jo",
        "orderDetails": [
            {
                "id": 3,
                "orderName": "order two"
            },
            {
                "id": 4,
                "orderName": "order one"
            }
        ]
    }
  ]
```
  
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
 - perform some form of reconciliation
 - notify customer (or consuming app) of successful dataload.

*Some basic test peformed with customer-data-loader returning approx 10000 records (not really large) on the JSON response and ingesting this into H2 DB via the customer-services application in less than 2 seconds.*

```
21:04:42.288  INFO 18348 --- [nio-8081-exec-2] a.c.d.c.controller.CustomerController    : loading customers..
21:04:42.947  INFO 18348 --- [nio-8081-exec-2] a.c.d.c.controller.CustomerController    : loading customer data of size= 10898
21:04:44.119  INFO 18348 --- [nio-8081-exec-2] a.c.d.c.controller.CustomerController    : customer data successfully loaded
```
 
