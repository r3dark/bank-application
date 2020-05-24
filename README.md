# Parking Lot Application
This is a Banking application which can
set up a branch, create accounts in the specific
branch and perform set of operations on
the bank account of customer for eg.
deposit money, withdraw money, view transaction
history, view mini statement (last 10 transactions)
and many more... all in real time.

## Getting started
These instructions will get you a copy of 
the project up and running on your local 
machine for development and testing purposes.

### Prerequisites
This project is designed and developed 
using following libraries and tools.
To run this application successfully and
avoid compatibility issues, please 
install / update them to at least the 
versions mentioned. Most of it will be handled
by Gradle.
- Java 1.8
- Gradle 5.5.1
- Git 2.21.0

### Setup
To setup the project, open terminal at root 
of project and execute : 

```
gradle build --refresh-dependencies
```

This will run the test suite, 
build the application with required dependencies, 
and get it ready for execution.

### Execute / Run
To run this project, either import it in
**Intellij Idea** or run the following command
at root of the project :

```
java -jar build/libs/bank-application-1.0-SNAPSHOT.jar
```

### How to use
You can import the **Postman Collection** provided in : 

```
src/main/resources/BankApp_PostmanCollection.json
```

or you can use [**Swagger**](http://localhost:8080/bank/swagger-ui.html) as well.


