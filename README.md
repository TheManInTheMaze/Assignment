# Assignment

#A few words
* I choosed to focus my work mainly on the model part of the application. Therefore the package model contains all the logic for a minesweep game to be played.
* In order to make it more fun, I exposed a webservice that we can access through POST and GET requests. However this webservice isn't fully developped as for example there is no persistence in database implemented so we can only play one game at a time an unit test to do not test game behavior, those tests are handled by the model.
* This webservice answers in Json an array that represent the field of the game, a client could them consume that data to display the game.


##PRE-REQUISITE

In order to build and run unit test you will need :
  * A java JDK
  * Standard Maven install
  
##BUILD

Clone this repo and execute from inside the directory:
* mvn clean install   (build the project in target & execute unit tests)
  
  
##HOW TO RUN THE PROJECT
 * java -jar target/com.assignment-1.0-SNAPSHOT.one-jar.jar
 * curl --data "xSize=3&ySize=3&nbMines=2" http://localhost:8080/game/api/minesweep  (game start)
 * curl http://localhost:8080/game/api/minesweep?xPos=1&yPos=1 (game click)
 * Hit enter to terminate server



