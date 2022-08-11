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
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if ("POST".equals(exchange.getRequestMethod())) {
            String requestBody = getRequestBodyAsString(exchange);
            try {
                System.out.println("try");
                handleResponse(exchange, requestBody);
            } catch (InterruptedException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        return httpExchange.
                getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];
    }

    private String getRequestBodyAsString(HttpExchange httpExchange) throws IOException {
        System.out.println("was post request");
        //TODO Потоки вывода превратить байты в строку
        String fightersAsString = new String((httpExchange.
                getRequestBody()).readAllBytes(), StandardCharsets.UTF_8);
        System.out.println(fightersAsString);
        return fightersAsString;
    }

    private void handleResponse(HttpExchange httpExchange, String requestBodyFighters) throws IOException, InterruptedException, SQLException {
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
