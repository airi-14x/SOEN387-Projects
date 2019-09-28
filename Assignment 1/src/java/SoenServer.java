/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jasminelatendresse
 */
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SoenServer {
      public static void main(String[] args) throws IOException {
      HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
      HttpContext context = server.createContext("/app");
      context.setHandler(SoenServer::handleRequest);
      server.start();
  }

  private static void handleRequest(HttpExchange exchange) throws IOException {
      String response = "Hi there!";
      exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
          try (OutputStream os = exchange.getResponseBody()) {
              os.write(response.getBytes());
          }
  }
}
