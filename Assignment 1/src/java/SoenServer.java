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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SoenServer {
      public static void main(String[] args) throws IOException {
      HttpServer server = HttpServer.create(new InetSocketAddress(8009), 0);
      HttpContext context = server.createContext("/");
      HttpContext indexContext1 = server.createContext("/index");
      HttpContext indexContext2 = server.createContext("/index2");
      
      context.setHandler(SoenServer::handleRequest1);
      indexContext1.setHandler(SoenServer::handleRequest1);
      indexContext2.setHandler(SoenServer::handleRequest2);

      server.start();
      System.out.println("Hit enter to exit");
      System.in.read();
      server.stop(0);
      System.out.println("Exit successful");
  }

  public static void handleRequest1(HttpExchange t) throws IOException {
    String path = System.getProperty("user.dir");
    File index1File = new File(path + "/web/index.html");
    String response = read(index1File);
    String requestURL = t.getRequestURI().toString();
    
    if(requestURL.equals("/")||requestURL.equals("/index")||requestURL.equals("/index.html")){
        sendOkResponse(t, response);
    }
    else{
       sendNotFoundResponse(t);
    }
  }
  
  public static void handleRequest2(HttpExchange t) throws IOException {
    String path = System.getProperty("user.dir");
    File index2File = new File(path + "/web/index2.html");
    String response = read(index2File);
    String requestURL = t.getRequestURI().toString();
    
    if(requestURL.equals("/index2") || requestURL.equals("/index2.html")){
        sendOkResponse(t, response);
    }
    else{
       sendNotFoundResponse(t);
    }
  }
  
  private static String read(File f) throws FileNotFoundException, IOException{
      String content = "", line;  
          try (BufferedReader br = new BufferedReader(new FileReader(f))) {
              while((line = br.readLine()) != null){
                  content += line;
              }   }
      return content;
  }
  
  private static void sendOkResponse(HttpExchange t, String response) throws IOException{
        String path = t.getRequestURI().getPath();
        if(path.endsWith(".html")||path.endsWith("")){
            t.getResponseHeaders().set("Content-Type", "text/html");
        }
        if(path.endsWith(".css")){
            t.getResponseHeaders().set("Content-Type", "text/css");
        }
        if(path.endsWith(".txt")){
            t.getResponseHeaders().set("Content-Type", "text/plain");
        }
        
        t.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = t.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
  
  private static void sendNotFoundResponse(HttpExchange t) throws IOException{
        String response = "<h1>404 NOT FOUND</h1>";
        t.getResponseHeaders().set("Content-Type", "text/html; charset UTF-8");
        t.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = t.getResponseBody()) {
            os.write(response.getBytes());
        } 
  }
}

