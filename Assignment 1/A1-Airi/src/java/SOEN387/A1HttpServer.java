package SOEN387;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        HttpContext css_context = server.createContext("/css/css-file1_1.css");
        HttpContext css2_context = server.createContext("/css/css-file2_1.css");

        index_context.setHandler(A1HttpServer::handleIndexRequest);
        another_index_context.setHandler(A1HttpServer::handleIndexRequest);
        index2_context.setHandler(A1HttpServer::handleIndex2Request);
        css_context.setHandler(A1HttpServer::handleCSSRequest);
        css2_context.setHandler(A1HttpServer::handleCSS2Request);

        server.start();
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter something to stop server: ");
        String input_value = reader.next();
        server.stop(0);
    }

    private static void handleIndexRequest(HttpExchange exchange) throws IOException {

        // Get current path //
        String current_path = System.getProperty("user.dir");
        String response = "";
        // Find index.html file --> /web/index.html //
        try (FileReader index_file = new FileReader(current_path + "/web/index.html");
                BufferedReader bufferedReader = new BufferedReader(index_file);) {
            // Read File //
            String current_line = "";

            while ((current_line = bufferedReader.readLine()) != null) {
                response += current_line;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found error!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading the file!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Get Context URI //
        String request_URI = exchange.getRequestURI().toString();

        exchange.getResponseHeaders().set("Content-Type", "text/html");

        exchange.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        } catch (Exception e) {
            System.out.println("Error with OutputStream");
            e.printStackTrace();
        }
    }

    private static void handleIndex2Request(HttpExchange exchange) throws IOException {

        // Get current path //
        String current_path = System.getProperty("user.dir");
        String response = "";
        // Find index2.html file --> /web/index2.html //
        try (FileReader index_file = new FileReader(current_path + "/web/index2.html");
                BufferedReader bufferedReader = new BufferedReader(index_file);) {

            // Read File //
            String current_line = "";

            while ((current_line = bufferedReader.readLine()) != null) {
                response += current_line;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found error!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading the file!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Get Context URI //
        String request_URI = exchange.getRequestURI().toString();

        exchange.getResponseHeaders().set("Content-Type", "text/html");

        exchange.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        } catch (Exception e) {
            System.out.println("Error with OutputStream");
            e.printStackTrace();
        }
    }

    private static void handleCSSRequest(HttpExchange exchange) throws IOException {

        // Get current path //
        String current_path = System.getProperty("user.dir");
        String response = "";
        // Find css-file1_1 file --> /web/css/css-file1_1.css //
        try (FileReader index_file = new FileReader(current_path + "/web/css/css-file1_1.css");
                BufferedReader bufferedReader = new BufferedReader(index_file);) {

            // Read File //
            String current_line = "";

            while ((current_line = bufferedReader.readLine()) != null) {
                response += current_line;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found error!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading the file!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Get Context URI //
        String request_URI = exchange.getRequestURI().toString();

        exchange.getResponseHeaders().set("Content-Type", "text/css");

        exchange.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        } catch (Exception e) {
            System.out.println("Error with OutputStream");
            e.printStackTrace();
        }
    }

    private static void handleCSS2Request(HttpExchange exchange) throws IOException {

        // Get current path //
        String current_path = System.getProperty("user.dir");
        String response = "";
        // Find css-file1_1 file --> /web/css/css-file1_1.css //
        try (FileReader index_file = new FileReader(current_path + "/web/css/css-file2_1.css");
                BufferedReader bufferedReader = new BufferedReader(index_file);) {

            // Read File //
            String current_line = "";

            while ((current_line = bufferedReader.readLine()) != null) {
                response += current_line;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found error!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading the file!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Get Context URI //
        String request_URI = exchange.getRequestURI().toString();

        exchange.getResponseHeaders().set("Content-Type", "text/css");

        exchange.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        } catch (Exception e) {
            System.out.println("Error with OutputStream");
            e.printStackTrace();
        }
    }

}
