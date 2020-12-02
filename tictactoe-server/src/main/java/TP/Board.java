package TP;
//import spark.Spark;
//import spark.Request;
//import spark.Response;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import static spark.Spark.*;

import java.util.ArrayList;

public class Board {

    private ArrayList<char[][]> savedGames = new ArrayList<char[][]>();
    private char[][] currentGame = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9',}};

    public char[][] getCurrentGame() {
        return currentGame;
    }

    public void move(char place, char symbol) {
		switch(place) {
		case '1':
			currentGame[0][0] = symbol;
			break;
		case '2':
			currentGame[0][1] = symbol;
			break;
		case '3':
			currentGame[0][2] = symbol;
			break;
		case '4':
			currentGame[1][0] = symbol;
			break;
		case '5':
			currentGame[1][1] = symbol;
			break;
		case '6':
			currentGame[1][2] = symbol;
			break;
		case '7':
			currentGame[2][0] = symbol;
			break;
		case '8':
			currentGame[2][1] = symbol;
			break;
		case '9':
			currentGame[2][2] = symbol;
			break;
		}
		
	}
}
