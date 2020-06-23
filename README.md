# Bank Application
This is a Bank application which can
set up a branch, create accounts in the specific
branch and perform set of operations on
the bank account of customer for eg.
deposit money, withdraw money, view transaction
history, view mini statement (last 10 transactions)
etc... all in real time.

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
Clone the repository to your local:

```sh
$ git clone https://github.com/r3dark/auto-complete.git
```

To set up the project, open terminal at root 
of project and execute : 

```sh
$ gradle clean test
$ gradle clean build --refresh-dependencies
```

This will run the test suite, 
build the application with required dependencies, 
and get it ready for execution.

### Execute / Run
To run this project, either import it in
**Intellij Idea** or run the following command
at root of the project :

```sh
$ java -jar build/libs/bank-application-1.0-SNAPSHOT.jar
```

### How to use
- Import the **Postman Collection** provided in : 

```
src/main/resources/BankApp_PostmanCollection.json
```

- Use [**Swagger**](http://localhost:8080/bank/swagger-ui.html) (recommended)


