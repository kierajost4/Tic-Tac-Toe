package TP;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Spark;
import spark.Request;
import spark.Response;
import static spark.Spark.*;
import com.google.gson.JsonElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestfulServer {

    private final Logger log = LoggerFactory.getLogger(RestfulServer.class);
    Board board;
    Player player1;
    Player player2;


    public RestfulServer(){
        this.board = new Board();
        this.player1 = new Player("Player 1", 'X');
        this.player2 = new Player("Player 2", 'O');
        this.configureRestfulApiServer();
        this.processRestfulApiRequests();
    }

    private void configureRestfulApiServer(){
        Spark.port(8080); //starts a Spark microserver listening on the port specified
        System.out.println("Server configured to listen on port 8080");
    }

    private void processRestfulApiRequests(){

        Spark.post("/", this::echoPost);
        Spark.post("/updateBoardP1", this::updateBoardP1);
        Spark.post("/updateBoardP2", this::updateBoardP2);
        Spark.get("/", this::echoRequest);
        Spark.get("/board", this::handleBoardRequests);
        Spark.get("/checkForWin", this::checkForWin);
        Spark.get("/saveAndReset", this::saveAndReset);

        //all other routes
    }

    public String saveAndReset(Request request, Response response){
        board.saveGame();
        board.resetBoard();
        return " ";
    }

    public String checkForWin(Request request, Response response){
        String winner = Character.toString(board.checkForWin());
        return winner;
    }

    public Object handleBoardRequests(Request request, Response response){
        Gson gson = new Gson();
        String json = gson.toJson(board.getCurrentGame());
        // process request
        return json;
    }

    private String echoRequest(Request request, Response response){
        //do these need to go in every request method?
        response.type("application/json");
        response.header("Access-Control-Allow-Origin", "*");
        response.status(200);
        //

        return HttpRequestToJson(request);
    }

    private String updateBoardP1(Request request, Response response){

        System.out.println(request.body());
        char position = request.body().charAt(11);
        System.out.println(position);
        board.move(position, player1.getIcon());
        return "";
    }

    private String updateBoardP2(Request request, Response response){

        System.out.println(request.body());
        char position = request.body().charAt(11);
        System.out.println(position);
        board.move(position, player2.getIcon());
        return "";
    }

    private String echoPost(Request request, Response response){
        System.out.println(request.body());
        return "";
    }

    private String HttpRequestToJson(Request request){
        return "{\n"
                + "\"attributes\":\"" + request.attributes()          + "\",\n"
                + "\"body\":\"" + request.body()                      + "\",\n"
                + "\"contentLength\":\"" + request.contentLength()    + "\",\n"
                + "\"contentType\":\"" + request.contentType()        + "\",\n"
                + "\"contextPath\":\"" + request.contextPath()        + "\",\n"
                + "\"cookies\":\"" + request.cookies()                + "\",\n"
                + "\"headers\":\"" + request.headers()                + "\",\n"
                + "\"host\":\"" + request.host()                      + "\",\n"
                + "\"ip\":\"" + request.ip()                          + "\",\n"
                + "\"params\":\"" + request.params()                  + "\",\n"
                + "\"pathInfo\":\"" + request.pathInfo()              + "\",\n"
                + "\"serverPort\":\"" + request.port()                + "\",\n"
                + "\"protocol\":\"" + request.protocol()              + "\",\n"
                + "\"queryParams\":\"" + request.queryParams()        + "\",\n"
                + "\"requestMethod\":\"" + request.requestMethod()    + "\",\n"
                + "\"scheme\":\"" + request.scheme()                  + "\",\n"
                + "\"servletPath\":\"" + request.servletPath()        + "\",\n"
                + "\"session\":\"" + request.session()                + "\",\n"
                + "\"uri()\":\"" + request.uri()                      + "\",\n"
                + "\"url()\":\"" + request.url()                      + "\",\n"
                + "\"userAgent\":\"" + request.userAgent()            + "\",\n"
                + "}";
    }

    public static void main(String[] programArgs){

        RestfulServer restfulServer = new RestfulServer(); // never returns, listens to requests
    }

}
