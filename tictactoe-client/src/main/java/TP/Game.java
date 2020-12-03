package TP;

import java.util.Scanner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;

public class Game {

	//example of url
	//http://localhost:8080/board
	//or other get path
	public static String httpGetRequest(String url) throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);
			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>(){
				@Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
			return responseBody;
        } finally {
            httpclient.close();
        }
	}

	public static String httpPostRequest(String endpoint, char position) throws Exception{
		String result = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try{
			HttpPost httpPost = new HttpPost(endpoint);

			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			String JsonInput = "{position: " + position + "}";

			StringEntity stringEntity = new StringEntity(JsonInput); 
			httpPost.setEntity(stringEntity);
			HttpResponse response = httpclient.execute(httpPost);


			BufferedReader br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatusLine().getStatusCode());
			}

			result = br.readLine();

		} finally{}
		return result;
	}

	public static char[][] formatBoard(String board){
		board = board.replace(",","");
		board = board.replace("\"","");
		board = board.replace("[","");
		board = board.replace("]","");
		String r1 = "" + board.charAt(0) + board.charAt(1) + board.charAt(2);
		String r2 = "" + board.charAt(3) + board.charAt(4) + board.charAt(5);
		String r3 = "" + board.charAt(6) + board.charAt(7) + board.charAt(8);
		char[] row1 = r1.toCharArray();
		char[] row2 = r2.toCharArray();
		char[] row3 = r3.toCharArray();
		char[][] currentGame = {row1,row2,row3};
		return currentGame;
	}

	public static void printBoard(char[][] currentGame){
		System.out.println("-------------");
		for(int i  = 0; i < 3; i++) {
			System.out.print("| " + currentGame[i][0] + " | " + currentGame[i][1] + " | " + currentGame[i][2] + " |");
			System.out.println();
			System.out.println("-------------");
		}
	}
	public static void main(String[] args) throws Exception {
		System.out.println("----Welcome to tictactoe!----");
		System.out.println("Get three in a row to win!");
		Scanner scan = new Scanner(System.in);
		boolean newGame = true;

		while(newGame) {
			boolean inGame = true;
			boolean turn = true;
			while(inGame) {
				try{
					String board = httpGetRequest("http://localhost:8080/board");
					printBoard(formatBoard(board));
				} finally{}
				if(turn){
					System.out.println("It's Player 1's turn.");
					System.out.print("Choose a number between 1 and 9 to play on: ");
					String input1 = scan.next();
					if(input1.length() != 1){
						System.out.println("This number is not in range! Player 1 try again.");
					}else{
						char position1 = input1.charAt(0);
						try{
							String valid = httpPostRequest("http://localhost:8080/checkIfValid",position1);
							if(valid.equals("in range and valid")){
								httpPostRequest("http://localhost:8080/updateBoardP1",position1);
								turn = false;
							} else if (valid.equals("not valid")) {
								System.out.println("This number has already been taken! Player 1 choose again");
							}else{
								System.out.println("This number is not in range! Player 1 choose again.");	
							}
						}finally{}
					}
				} else{  
					System.out.println("It's Player 2's turn.");
					System.out.print("Choose a number between 1 and 9 to play on:");
					String input2 = scan.next();
					if(input2.length() != 1){
						System.out.println("This number is not in range! Player 2 try again.");
					} else {
						char position2 = input2.charAt(0);
						try{
							String valid = httpPostRequest("http://localhost:8080/checkIfValid",position2);
							if(valid.equals("in range and valid")){
								httpPostRequest("http://localhost:8080/updateBoardP2",position2);
								turn = true;
							} else if (valid.equals("not valid")) {
								System.out.println("This number has already been taken! Player 2 try again");
							}else{
								System.out.println("This number is not in range! Player 2 try again.");	
							}
						}finally{}
					}
				}
				try{
					String w = httpGetRequest("http://localhost:8080/checkForWin");
					char winner = w.charAt(0);
					//System.out.println("JSON:" + winner + ".");
					if(winner != ' '){
						try{
							String b = httpGetRequest("http://localhost:8080/board");
							printBoard(formatBoard(b));
						} finally{}
						
						if(winner == 'X') {
							System.out.println("Player 1 wins this game!");
							inGame = false;
						}
						if(winner == 'O') {
							System.out.println("Player 2 wins this game!");
							inGame = false;
						}
					}
				}finally{}
			}
			System.out.print("Want to start a new game?(y/n):");
			if(scan.next().charAt(0) == 'n') {
				newGame = false;
			}

			try{
				httpGetRequest("http://localhost:8080/saveAndReset");
			}finally{};
		}
		System.out.println("GOODBYE!");
		scan.close();
	}
}
