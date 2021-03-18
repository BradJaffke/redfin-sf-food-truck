# SF Food Truck Finder

This is a simple command-line tool that displays food trucks that are currently open in the city of San Francisco.  This information comes from an API hosted by the city of San Francisco.  More information about it can be found [on their website](https://dev.socrata.com/foundry/data.sfgov.org/jjew-r69b).

### Prequisites
To build this project, ensure you have (at the minimum) Java 11 installed.  The best place to find this is on Oracle's website [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

### Build

Navigate to the root directory of the project, and run this command:

`./gradlew build`

NOTE: This may take a few minutes if you have never installed Gradle before.

### Run

Ensure you are still in the root directory of the project, and run this command:

`./gradlew run`

### Pagination

The SF Food Truck finder will display results in groups of 10.  After it displays 10 it will wait for user input.  

In order to move on:

1. To Exit the program: Type '0' and then hit the enter key
2. To Move to the next 10 results: type any other key and then hit the enter key