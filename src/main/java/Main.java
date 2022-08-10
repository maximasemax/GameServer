import HttpHandler.MyHttpHandlerForFight;
import HttpHandler.MyHttpHandlerForItem;
import HttpHandler.MyHttpHandlerForPerson;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {


    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8002), 0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server.createContext("/fight", new MyHttpHandlerForFight());
        server.createContext("/getPerson", new MyHttpHandlerForPerson());
        server.createContext("/getItem", new MyHttpHandlerForItem());
        server.setExecutor(threadPoolExecutor);
        server.start();

    }
}
