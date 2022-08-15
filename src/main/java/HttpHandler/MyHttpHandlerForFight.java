package HttpHandler;

import JsonBild.JsonBuildForFight;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.impl.FighterConfiguration;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class MyHttpHandlerForFight implements HttpHandler {

    private static final String POST = "POST";

    @Override
    public void handle(HttpExchange exchange){

        if (POST.equals(exchange.getRequestMethod())) {
            String requestBody = null;
            try {
                requestBody = getRequestBodyAsString(exchange);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            try {
                System.out.println("try");
                handleResponse(exchange, requestBody);
            } catch (SQLException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String getRequestBodyAsString(HttpExchange httpExchange) throws IOException {
        System.out.println("was post request");
        String fightersAsString = new String((httpExchange.
                getRequestBody()).readAllBytes(), StandardCharsets.UTF_8);
        System.out.println(fightersAsString);
        return fightersAsString;
    }

    private void handleResponse(HttpExchange httpExchange, String requestBodyFighters) throws IOException, SQLException {
        JsonBuildForFight jsonBuild = new JsonBuildForFight();
        FighterConfiguration fighterConfiguration = jsonBuild.fromJsonFighter(requestBodyFighters);
        Fight fight = new Fight();
        String response = jsonBuild.parserFighter(fight.fight(fighterConfiguration));
        // this line is a must
        System.out.println(response);
        final byte[] rawResponseBody = response.getBytes();
        httpExchange.sendResponseHeaders(200, rawResponseBody.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(rawResponseBody);
        os.close();
    }
}
