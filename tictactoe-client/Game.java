import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.*;
import java.util.*;


public class Game {

	/*public static void sendGET(String getURL) throws IOException {
		URL obj = new URL(getURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		//con.setRequestProperty("User-Agent", usrAgent);
		int responseCode = con.getResponseCode();
		System.out.println("GET response code :: " + responseCode);
		if(responseCode == HttpURLConnection.HTTP_OK){
			//success 
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
			//print result
			System.out.println(response.toString());
		}else {
			System.out.println("GET request didnt work");
		}
	}
	*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	try{
		System.out.println("----Welcome to tictactoe!----");
		System.out.println("Get three in a row to win!");

		URL url = new URL("http://localhost:8080/board");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		int status = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while((inputLine = in.readLine()) != null){
			content.append(inputLine);
		}
	
		StringBuilder fullResponseBuilder = new StringBuilder();

		fullResponseBuilder.append(con.getResponseCode())
  		.append(" ")
  		.append(con.getResponseMessage())
		.append("\n");

		con.getHeaderFields().entrySet().stream()
  		.filter(entry -> entry.getKey() != null)
  		.forEach(entry -> {
      	fullResponseBuilder.append(entry.getKey()).append(": ");
      	List headerValues = entry.getValue();
      	Iterator it = headerValues.iterator();
      	if (it.hasNext()) {
          	fullResponseBuilder.append(it.next());
          	while (it.hasNext()) {
              	fullResponseBuilder.append(", ").append(it.next());
          	}
      	}
      	fullResponseBuilder.append("\n");
		});
		
		System.out.println(fullResponseBuilder.toString());
	  
	  	in.close();
		con.disconnect();

	} catch(IOException e){
		System.out.println("didnt work");
	}

	



	
		
		//Scanner scan = new Scanner(System.in);
		
		//Player p1 = new Player("Player 1", 'X');
		//Player p2 = new Player("Player 2", 'O');
		
		//Board game = new Board();
		//dont need this here, Server will create new instance of board?
		//boolean newGame = true; idk yet


		
		

		/*while(newGame) {
			
			boolean inGame = true, turn = true;
			while(inGame) {
				game.printBoard();
				
				if(turn) {
					System.out.println("It's " + p1.getName() + "'s turn.");
					System.out.print("Choose a number to play on:");
					game.move(scan.next().charAt(0), p1.getIcon());
					turn = false;
				}else {
					System.out.println("It's " + p2.getName() + "'s turn.");
					System.out.print("Choose a number to play on:");
					game.move(scan.next().charAt(0), p2.getIcon());
					turn = true;
				}
				
				char winner = game.checkForWin();
				if(winner != ' ') {
					inGame = false;
					if(p1.getIcon() == winner) {
						System.out.println(p1.getName() + " wins this game!");
						p1.incWins();
						p2.incLosses();
					}
					if(p2.getIcon() == winner) {
						System.out.println(p2.getName() + " wins this game!");
						p2.incWins();
						p1.incLosses();
					}
					inGame = false;
				}
			}
			
			System.out.print("Want to start a new game?(y/n):");
			if(scan.next().charAt(0) == 'n') {
				newGame = false;
			}
				game.achiveGame();
				game.resetBoard();	
		}
		
		
		scan.close();
		System.out.println("Goodbye!");
		*/
	}
	
//	private boolean validIcon(char icon) {
//		if(icon == ' ' || icon == '|' || icon == '-') {
//			return false;
//		}
//		
//		return true;
//	}


}

