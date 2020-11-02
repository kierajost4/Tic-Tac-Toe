package TP;
import spark.Spark;
import spark.Request;
import spark.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.ArrayList;

import static spark.Spark.*;

public class Board {

    private ArrayList<char[][]> savedGames = new ArrayList<char[][]>();
    private char[][] currentGame = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' ',}};

    private static char[][] boardPositions = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};


    public char[][] getCurrentGame() {
        return currentGame;
    }

    public char[][] getBoardPositions() {
        return boardPositions;
    }
}
