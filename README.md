# JAVA ecommerce app.
App built with Java (Java SE 19 & JDK 19).

## How it works
The app is a fake ecommerce app.
Using the command line the user can choose between 5 different operations: view the items and their details, purchase an item, return an item, sign up, get a csv file with all the available products of that day, exit the program.

The project contains 3 csv files (prodotti, utenti, vendite) that store all the information needed to make the app work properly.

## Compilation and execution
The app uses Apache Maven to compile the project and create the jar file.
### How-to:
Use `mvn compile package` to generate the target folder, this will include all the java files compiled and the jar file.
To execute the application use `java -jar target/application.jar`.
