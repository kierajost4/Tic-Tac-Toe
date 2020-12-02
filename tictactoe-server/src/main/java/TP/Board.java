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

  public char checkForWin() {
    for(int i = 0; i < 3; i++) {
      if(currentGame[i][0] == currentGame[i][1] && currentGame[i][1] == currentGame[i][2] && hasBlankCheck(currentGame[i][0],currentGame[i][1],currentGame[i][2])) {
        return currentGame[i][0];
      }
      if(currentGame[0][i] == currentGame[1][i] && currentGame[1][i] == currentGame[2][i] && hasBlankCheck(currentGame[0][i],currentGame[1][i],currentGame[2][i])) {
        return currentGame[0][i];
      }
    }

    if(currentGame[0][0] == currentGame[1][1] && currentGame[1][1] == currentGame[2][2] && hasBlankCheck(currentGame[0][0],currentGame[1][1],currentGame[2][2])) {
      return currentGame[0][0];
    }
    if(currentGame[0][2] == currentGame[1][1] && currentGame[1][1] == currentGame[2][0] && hasBlankCheck(currentGame[0][2],currentGame[1][1],currentGame[2][0])) {
      return currentGame[0][2];
    }
    return ' ';
  }

  private boolean hasBlankCheck(char c1, char c2, char c3) {
    if(c1 == ' ' || c2 == ' ' || c3 == ' ') {
      return false;
    }
    return true;
  }

  public void resetBoard() {
    currentGame[0][0] = '1';
    currentGame[0][1] = '2';
    currentGame[0][2] = '3';
    currentGame[1][0] = '4';
    currentGame[1][1] = '5';
    currentGame[1][2] = '6';
    currentGame[2][0] = '7';
    currentGame[2][1] = '8';
    currentGame[2][2] = '9';
    System.out.println("Board Reset!");
  }

  public void saveGame() {
    savedGames.add(currentGame);
  }
}
