# Assignment

##PRE-REQUISITE

In order to build and run unit test you will need :
  -A java JDK
  -Standard Maven install
  
##BUILD

Clone this repo and execute from inside the directory:
  mvn clean install   (build the project in target
  
  
##HOW TO RUN THE PROJECT
  java -jar target/com.assignment-1.0-SNAPSHOT.one-jar.jar
  Hit enter to terminate server
###To simulate traffic (game start):
  curl --data "xSize=3&ySize=3&nbMines=2" http://localhost:8080/game/api/minesweep
###To simulate traffic (game click):
  curl http://localhost:8080/game/api/minesweep?xPos=1&yPos=1



