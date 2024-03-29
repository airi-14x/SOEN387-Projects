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
import java.util.HashMap;
import java.util.Map;

public class SoenServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8009), 0);
        HttpContext context = server.createContext("/");
        HttpContext indexContext1 = server.createContext("/index.html");
        HttpContext indexContext2 = server.createContext("/index2.html");
        HttpContext httpResponse = server.createContext("/Assignment%201/src/java/SoenServlet.java");
        HttpContext cssContext1 = server.createContext("/css/css_file1.css");
        HttpContext cssContext2 = server.createContext("/css/css_file2.css");

        context.setHandler(SoenServer::handleRequest1);
        indexContext1.setHandler(SoenServer::handleRequest1);
        indexContext2.setHandler(SoenServer::handleRequest2);
        httpResponse.setHandler(new RequestHandler());
        cssContext1.setHandler(SoenServer::cssHandler1);
        cssContext2.setHandler(SoenServer::cssHandler2);

        server.start();
        System.out.println("Hit enter to exit");
        System.in.read();
        server.stop(0);
        System.out.println("Exit successful");
    }

    public static void handleRequest1(HttpExchange t) throws IOException {
        String path = System.getProperty("user.dir");
        File index1File = new File(path + "/web/index.html");
        String response = "";
        String requestURL = t.getRequestURI().toString();

        if (requestURL.equals("/") || requestURL.equals("/index.html")) {
            response = read(index1File);
            sendOkResponse(t, response);
        } else if (requestURL.contains("/SoenServlet")) {
            RequestHandler handler = new RequestHandler();
            handler.handle(t);
        } else {
            sendNotFoundResponse(t);
        }
    }

    public static void handleRequest2(HttpExchange t) throws IOException {
        String path = System.getProperty("user.dir");
        File index2File = new File(path + "/web/index2.html");
        String response = "";
        String requestURL = t.getRequestURI().toString();

        if (requestURL.equals("/") || requestURL.equals("/index2.html")) {
            response = read(index2File);
            sendOkResponse(t, response.toString());
        } else if (requestURL.contains("/SoenServlet")) {
            RequestHandler handler = new RequestHandler();
            handler.handle(t);
        } else {
            sendNotFoundResponse(t);
        }
    }

    public static void cssHandler1(HttpExchange t) throws FileNotFoundException, IOException {
        String currentPath = System.getProperty("user.dir");
        File cssFile = new File(currentPath + "/web/css/css_file1.css");
        String response = read(cssFile);
        t.getResponseHeaders().set("Content-Type", "text/css; charset UTF-8");
        sendOkResponse(t, response);
    }

    public static void cssHandler2(HttpExchange t) throws FileNotFoundException, IOException {
        String currentPath = System.getProperty("user.dir");
        File cssFile = new File(currentPath + "/web/css/css_file2.css");
        String response = read(cssFile);
        t.getResponseHeaders().set("Content-Type", "text/css; charset UTF-8");
        sendOkResponse(t, response);
    }

    private static String read(File f) throws FileNotFoundException, IOException {
        String content = "", line;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            while ((line = br.readLine()) != null) {
                content += line;
            }
        } catch (FileNotFoundException e) {
            System.out.print("<h1>404 NOT FOUND</h1>");
            e.printStackTrace();
        }
        return content;
    }

    public static void sendOkResponse(HttpExchange t, String response) throws IOException {
        t.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = t.getResponseBody()) {
            os.write(response.getBytes());
        }

    }

    private static void sendNotFoundResponse(HttpExchange t) throws IOException {
        String response = "<h1>404 NOT FOUND</h1>";
        t.getResponseHeaders().set("Content-Type", "text/html; charset UTF-8");
        t.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = t.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }
}
