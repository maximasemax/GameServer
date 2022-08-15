package HttpHandler;

import DataBase.DBForPerson;
import JsonBild.JsonBuildForPerson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class MyHttpHandlerForPerson implements HttpHandler {

    private static final String GET = "GET";

    @Override
    public void handle(HttpExchange exchange) {
        if (GET.equals(exchange.getRequestMethod())) {
            try {
                handleResponse(exchange);
            } catch (SQLException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void handleResponse(HttpExchange httpExchange) throws IOException, SQLException {
        JsonBuildForPerson jsonBuildForPerson = new JsonBuildForPerson();
        DBForPerson dbForPerson = new DBForPerson();
        String response = jsonBuildForPerson.convertPersonsToJson(dbForPerson.getPersonsFromDataBase());
        // this line is a must
        final byte[] rawResponseBody = response.getBytes();
        httpExchange.sendResponseHeaders(200, rawResponseBody.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(rawResponseBody);
        os.close();
    }
}