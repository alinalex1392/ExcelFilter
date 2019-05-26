# ExcelFilter
This java program is intended to do create pivots regarding a business logic from an excel file

## Install Steps


1. Run mvn clean install, this step will create two jar in the target path:
    * First jar is a simple jar without all the dependencies: named **original-jar-name**
    * Second jar is fat jar named executable jar: named **jar-name**




## Executable step

1. Use the fat jar to execute the program with the following command:

java -jar "Name of the fat jar" firstParam

* **firstParam**: is the location of the excel

**Example:**    java -jar pendingsProject-1.0-SNAPSHOT.jar C:\MyStuff\ExcelFilter\src\main\resources\SampleDataMircea.xlsx
