# Tic-Tac-Toe
##### Authors:
Kiera Jost,
Wyatt Poulos,
Megan Booher

## Description
The purpose of our project was for us to gain experience with RESTful APIs, Docker, Kubernetes, Git, and working as a team by developing a program for tic-tac-toe games and tournaments. Instead of developing this game as a monolithic application like we were used to, we achieved our learning goals by implementing a RESTful API web server and containerizing it inside a Docker image, developing and containerizing a client inside another Docker image, and managing these two containers inside one Kubernetes pod using minikube. 

#### tictactoe-client
This directory containes our game driver. The client application gives the users options to view past games, play again, reset the history, or leave the game. Based on the users input, it makes the appropriate HTTP requests to the server and provides the appropriate output to the users. Our design requires two users to be on the same machine and playing the game on the same instance of the client.

#### tictactoe-server
This directory contains our RESTFUL API web server. It services the HTTP request made by the client application. It also contains the logic of the game, keeps track of past games, the score, and the game number until the user chooses to reset the game history. As long as the kubernetes pod stays running, the users can leave the game and reattach to the client container at any point to continue at the game number they left off at.


## Minikube Usage

Run the kubernetes pod:
```
kubectl apply -f deploy.yaml
```
Attatch to the client container:
```
kubectl attach pod/tictactoe-pod -c client -i -t
```
This will run bash as it is the entrypoint for the client container.

Run the client application:
```
java -jar target/TP-jar-with-dependencies.jar Game.java
```

## Notes from Authors

### Design Downfalls
This project was successful in introducing us to a number of interesting technologies. However, as we began to understand the purpose of these technologies better, we now realize that because of the simplicity of our project, there are a number of downfalls to our design if our application were to be widely used by the public. We chose to to view this as another learning opportunity.

##### 1. User friendliness: 
The complexity and quantity of commands needed to simply begin the program essentially makes the application useless and convoluted to anyone other than
those who’ve created it. In the future, we would design a user friendly website for the users to interact with in place of the client application. 

##### 2. Scalability
Only one pair of players can interact with the the web server at once. These players also have to be on the same instance of the client, on the same machine. In the real world, our server should not have been designed to only provide one instance of the board and players. It also should have been designed so that the users can play each other on separate instances of the client, on different machines. This would involve redisigning the application to breaking out the client from the server, and allow the server and the client to scale separatley.

##### 3. Non-Persistant Storage
The past games are stored in the memory of the web server. This is an obvious issue because 
1. this would take up too much memory if the users don't choose to reset the history. 
2. The stroage isn't persistant. if the Kubernetes pod is redeployed or if the server is restarted, all saved games, the current game number, and the score is lost. 
In the future, we would store past games in a database for persistant storage. 

##### 4. Lack of High Avalability:
Our system is not highly available. If a pod were to crash we would have no way of transferring the workload to another pod and let alone no way of knowing it crashed. In the future, we could implement DaemonSets to monitor the node. This would allow us to monitor all of the pods and take action if one of them were to crash. This would provide failure detection and thus we would be able to move service to a new pod and create a high availability and good user experience.

##### Future Considerations
We chose to to view these downfalls as another learning opportunity. Upon further research, we have deeply considered the ways in which we could redesign our project to effectively utalize containerization and make it more appropriate for public use. Ask us about this!

##### Takaways
This process taught is a lot! Not only did we effectvely work as a team, but we got to research concepts and implement technology that was completely new to us. From this, we’ve gained experience with analyzing a lot of documentation and learned new ways of problem solving. Implementing this project took research about RESTful APIs, HTTP protocol, Docker, Kubernetes, and Git. After all of that, we continued research about how to make our project better in the future. We are excited to use the several skills and concepts we learned in future classes, internships, and jobs.



