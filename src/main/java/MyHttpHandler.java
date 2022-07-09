import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestParamValue = null;

        if ("GET".equals(exchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(exchange);
        } else if ("POST".equals(exchange)) {
            requestParamValue = handlePostRequest(exchange);
        }

        handleResponse(exchange);
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        return httpExchange.
                getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];
    }

    private String handlePostRequest(HttpExchange httpExchange){
        return httpExchange.getRequestBody().
    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();


        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("").
                append("").
                append("<h1>").
                append("Hello ")
                .append(requestParamValue)
                .append("</h1>")
                .append("")
                .append("");

        // encode HTML content
        String response = "gg";

        // this line is a must
        httpExchange.sendResponseHeaders(200, response.length());

        outputStream.write(response.getBytes());
        outputStream.flush();
        outputStream.close();


    }
}
