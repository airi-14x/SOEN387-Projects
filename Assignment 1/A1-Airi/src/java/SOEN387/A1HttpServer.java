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
public class A1HttpServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);

        HttpContext index_context = server.createContext("/");
        HttpContext another_index_context = server.createContext("/index.html");
        HttpContext index2_context = server.createContext("/index2.html");
        HttpContext css_context = server.createContext("/css/css-file1.css");
        HttpContext css2_context = server.createContext("/css/css-file2.css");

        index_context.setHandler(A1HttpServer::handleRequest);
        another_index_context.setHandler(A1HttpServer::handleIndexRequest);
        index2_context.setHandler(A1HttpServer::handleIndex2Request);
        css_context.setHandler(A1HttpServer::handleCSSRequest);
        css_context.setHandler(A1HttpServer::handleCSS2Request);

        server.start();
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter something to stop server: ");
        String input_value = reader.next();
        server.stop(0);
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {
        String response = "Hi there!";

        Scanner scanner = new Scanner(new File("index.html"));
        String temp_input = "";
        while (scanner.hasNext()) {
            temp_input += scanner.next();
        }
        System.out.println(temp_input);
        scanner.close();
        //exchange.sendResponseHeaders(200, temp_input.getBytes().length);
        //exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(temp_input.getBytes());
        os.close();
    }

    private static void handleIndexRequest(HttpExchange exchange) throws IOException {

    }

    private static void handleIndex2Request(HttpExchange exchange) throws IOException {

    }

    private static void handleCSSRequest(HttpExchange exchange) throws IOException {

    }

    private static void handleCSS2Request(HttpExchange exchange) throws IOException {

    }
}
