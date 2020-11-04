# Term Project: TP-D2
##### Authors:
Kiera Jost,
Wyatt Poulos,
Megan Booher

## Description
The purpose of our project is to create a Tic-Tac-Toe game by enabling communication between the client (the game driver) and a web server.  

##### tictactoe-server
This is a working, RESTful web server. It is able to receive HTTP requests and log the request body to STD out. *tictactoe-server* will eventually contain the logic of the Tic-Tac-Toe game and store past games. It will also service all HTTP requests needed from the client (game driver).
##### tictactoe-client 
This will eventually be the containerized client that communicates with the web server. It will be responsible for taking in the input from the players, sending HTTP requests to the web server, and producing the output to the players.

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
docker run -p 8080:8080 my image
``` 
