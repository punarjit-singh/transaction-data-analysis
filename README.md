#Transaction Data Analysis

This project provides methods to analyse ```src/main/resources/transaction.csv``` data.

It performs data driven unit tests for the above mentioned methods.

### Project Pattern:

The project follows the single responsibility principle for all methods.

The project was designed on Mac OS Mojave using IntelliJ Idea 2019 community edition.

### Project Structure:

```markdown
- src
    - main
        - java
            - org.nasdaq
                - models
                    - Contains models to map CSV data to Java Object
                - services
                    - Contains services e.g. TransactionsService that contain the required methods
                - utils
                    - Contains utils to read Transactions csv and perform calculations 
        - resources
    - test  
        - org.nasdaq.test   
                - Contains tests e.g. TransactionTest for services e.g. TransactionsService
```

### Dependencies:
* Java 8
* Maven
* TestNG 7.1.0
* IntelliJ TestNG plugin if running tests from IntelliJ

### Clone the repo
```markdown
git clone https://github.com/punarjit-singh/transaction-data-analysis.git
```

### Project structure and compiler settings for IntelliJ
* In Project Structure settings > Modules > Sources - Select language level 8 and click apply.
* In IDE settings/preferences > Build, Execution, Deployment > Compiler > Java Compiler - Make sure Project Bytecode version is set to 8

### Running the tests

* Using mvn command line:
  * ```mvn clean install```
  
* Using IntelliJ TestNG plugin runner:
  * Right click on the test class inside ```org.nasdaq.test``` and select the Run "\<TestClass>" option
  
### Reports
Reports generated at ```target/surefire-reports/index.html```