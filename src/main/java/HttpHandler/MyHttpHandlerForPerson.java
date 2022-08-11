package HttpHandler;

import DataBase.DBForPerson;
import JsonBild.JsonBuildForPerson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class MyHttpHandlerForPerson implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            try {
                handleResponse(exchange);
            } catch (InterruptedException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void handleResponse(HttpExchange httpExchange) throws IOException, InterruptedException, SQLException {
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