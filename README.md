# Ovarro Software Engineer Technical Test

## Technology Choices
I chose Java to develop this application as I wanted to include automated tests in the development. I am most familiar with writing JUnit tests and was able to create an automated test suite with a high test coverage. Java is an object-oriented programming language, which helped in modelling the different aspects of ConnectFour. Java also allowed me to create documentation to go with my submission with javadocs. 

My application meets the following requirements:
* Allow the player(s) to configure the size of the grid and the winning row length (i.e. A player could need 4, 5, or 3 tokens, etc. in a row to win the game)
* Initialise a new game grid.
* Add a token to the game grid. A token can be either Red or Yellow. A token is added to a specified grid column.
* Show the game grid state/progress in the console or log to a file.
* Show the next player to play, Red or Yellow.
* Show the winner.

I used Maven to automate the build process for the unit tests. I used Eclipse as my IDE, for which Maven was included in the installation.

## Instructions

Below are steps to set up on a Mac/Linux environment. Please note that this code was developed in Windows. 

### Setup
1. Open the project in an IDE of your choice. **Eclipse** was used to develop the app, so it is recommended.
2. **Configure Build Path** - To configure the build path in eclipse, right click on the project -> Build Path -> Configure Build Path -> Libraries.
3. **Add Libraries** - Add your JDK and JUnit5 to the Modulepath and Maven dependencies to the Classpath. Apply and close.
4. **Running tests** - Right click on GameTest.java -> Debug As JUnit test

### Running Application
It is assumed that Java is installed and is accessible from the terminal. Check if java is installed through 
```
java -version
```
If not, install the latest JDK for your system and ensure that it can be accessed from the terminal.

To compile, run:
```
javac Game.java
```
To run:
```
java Game
```
Please let me know if there are any issues during installation. 



