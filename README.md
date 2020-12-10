Tic-Tac-Toe
##### Authors:
Kiera Jost,
Wyatt Poulos,
Megan Booher

## Description
The purpose of our project was for us to gain experience with RESTful APIs, Docker, Kubernetes, Git, and working as a team by developing a program for tic-tac-toe games and tournaments. Instead of developing this game as a monolithic application like we were used to, we achieved our learning goals by implementing a RESTful API web server and containerizing it inside a Docker image, developing and containerizing a client inside another Docker image, and managing these two containers inside one Kubernetes pod. 

#### tictactoe-client
This directory containes our game driver. The client application gives the users options to view past games, play again, reset the history, or leave the game. Based on the users input, it makes the appropriate HTTP requests to the server and provides the appropriate output to the users. Our design requires two users to be on the same machine and playing the game on the same instance of the client.

#### tictactoe-server
This directory contains our RESTFUL API web server. It services all request made by the client application. It also contains the logic of the game, keeps track of past games, the score, and the game number until the user chooses to reset the game history. As long as the kubernetes pod stays running, the users can leave the game and reattach to the client at any point to continue at whatever game number they left off at.



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
