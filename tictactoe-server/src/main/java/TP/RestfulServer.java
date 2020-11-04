package TP;
import com.google.gson.Gson;
import spark.Spark;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestfulServer {

    private final Logger log = LoggerFactory.getLogger(RestfulServer.class);
    Board board;

    public RestfulServer(Board board){
        this.board = board;
        this.configureRestfulApiServer();
        this.processRestfulApiRequests();
    }

    private void configureRestfulApiServer(){
        Spark.port(8080); //starts a Spark microserver listening on the port specified
        System.out.println("Server configured to listen on port 8080");
    }

    private void processRestfulApiRequests(){

        Spark.post("/", this::echoPost);
        Spark.get("/", this::echoRequest);
        Spark.get("/board", this::handleBoardRequests);
        Spark.get("/boardPositions", this::handleBoardPositionsRequests);



        //all other routes
    }

    public Object handleBoardRequests(Request request, Response response){
        Gson gson = new Gson();
        String json = gson.toJson(board.getCurrentGame());
        // process request
        return json;
    }

    public Object handleBoardPositionsRequests(Request request, Response response) {
        Gson gson = new Gson();
        String json = gson.toJson(board.getBoardPositions());
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

    //TODO
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
        Board board = new Board();
        RestfulServer restfulServer = new RestfulServer(board); // never returns, listens to requests
    }

}
