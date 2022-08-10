# ENSF 409 Final Project - Supply Chain Management (Group 24)

## Contributors
---
- Ahad Ali
- Harshit Sharma
- Muhammad Khan
- Shamis Ali

## About The Project
---
This project is a supply chain management system which reads from a databse that is built up of manufacturers and 4 different kinds of furniture. It then takes in input from the user and provides the user with a order form that displays the cheapest combination of partial furniture which give a complete furniture.

## Demo Video Link
---
[Demo Video](https://youtu.be/kQP21419Rw4)
## Usage
---
### Run Application via Terminal
1. Assuming you have extracted zip folder, now open your desired command prompt.
2. Make sure you have the inventory.sql and in the MySQL command line client, create the inventory database using the command "source path-to-inventory-file" or alternatively use SQL workbench.
3. Next to compile, use command,
```bash
javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/controller/Logic.java
```
4. Finally to run, use command,
```bash
java -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/controller/Logic
```

### Run Unit Tests via Terminal
#### Make sure you refresh inventory.sql database after every test!!!
1. Again, assuming you have extracted zip folder, now open your desired command prompt.
2. Next to compile, use command,
```bash
javac -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/tests/<TestClassName.java>
```
3. Finally to run, use command,
```bash
java -cp .;lib/hamcrest-core-1.3.jar;lib/junit-4.13.2.jar;lib/mysql-connector-java-8.0.23.jar org.junit.runner.JUnitCore edu.ucalgary.ensf409.tests.<TestClassName>
```
##### Note: Replace ; with : if using linux/mac.

## UML Diagram
![UML Diagram](https://github.com/March-27-Hackathon/supply-chain-management-Harsh-S7/blob/main/ENSF%20409%20Final%20Project.png)
##### Note: Image also available in root folder

