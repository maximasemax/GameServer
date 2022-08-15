package HttpHandler;

import DataBase.DBForItem;
import JsonBild.JsonBuildForFight;
import JsonBild.JsonBuildForItem;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class MyHttpHandlerForItem implements HttpHandler {

    private static final String GET = "GET";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (GET.equals(exchange.getRequestMethod())) {
            try {
                handleResponse(exchange);
            } catch (InterruptedException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleResponse(HttpExchange httpExchange) throws IOException, InterruptedException, SQLException {
        JsonBuildForItem jsonBuildForItem = new JsonBuildForItem();
        DBForItem dbForItem = new DBForItem();
        String response = jsonBuildForItem.parserItems(dbForItem.getItemsFromDataBase());
        // this line is a must
        final byte[] rawResponseBody = response.getBytes();
        httpExchange.sendResponseHeaders(200, rawResponseBody.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(rawResponseBody);
        os.close();
    }
}
