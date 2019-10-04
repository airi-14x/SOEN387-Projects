package SOEN387;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Airi
 */
public class BasicHttpServerExample {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(BasicHttpServerExample::handleRequest);
        server.start();
        Scanner reader = new Scanner(System.in);
        String input_value = reader.next();
        //reader.close();
        server.stop(0);
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {
        String response = "Hi there!";

        Scanner scanner = new Scanner(new File("index.html"));
        String temp_input = "";
        while (scanner.hasNext()) {
            temp_input += scanner.next();
        }
        scanner.close();
        exchange.sendResponseHeaders(200, temp_input.getBytes().length);
        //exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(temp_input.getBytes());
        os.close();
    }
}
