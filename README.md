Tic-Tac-Toe
##### Authors:
Kiera Jost,
Wyatt Poulos,
Megan Booher

## Description
The purpose of our project was of us to gain experience with RESTful APIs, Docker, Kubernetes, Git, and working as a team through developing a program for tic-tac-toe games and tournaments. Instead of developing this game as a monolithic application like we were used to, we achieved our learning goals by implementing a RESTful API web server and containerizing it inside a Docker image, developing and containerizing a client inside another Docker image, and managing these two containers inside one Kubernetes pod. 

##### tictactoe-server
The RESTful API contains all the logic of the game, the current state of the game, the score, and stores past games. 

##### tictactoe-client 
The client acts as a game driver to take in input from the users, communicate with the web server, and provide the users with the correct output. 



Our design requires two users to be on the same machine and playing the game on the same instance of the client. The client application gives the users options to view past games, play again, reset the history, or leave the game. As long as the kubernetes pod stays running, the server will keep track of old gamesm the score, and the game number until the user chooses to reset the history. If the users leaves the game, they can reattach to the client at any point to continue at whatever game number they left off at.



## Usage
To compile the RESTful web server, make sure you are at the root of the the *tictactoe-server* directory, and run the command:
```
bash mvn-build.sh
```
This is a bash script that runs the commands:
1. mvn clean
2. mvn package

Then to run the application, run the command:
```
java -cp target/TP-jar-with-dependencies.jar TP.RestfulServer
```
## Docker
Use the *Dockerfile* to build and run the RESTful web server in a containerized environment.

To use the *Dockerfile* to build a docker image, make sure you are at the root of the *tictactoe-server* directory, and run the command:
```
docker build -t myimage .
```
Then, to run the Docker image as a container, run the command:
```
docker run -p 8080:8080 myimage
``` 
