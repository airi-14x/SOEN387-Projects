
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jasminelatendresse
 */
public class RequestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException {
        StringBuilder response = new StringBuilder();
        if (t.getRequestMethod().equalsIgnoreCase("POST")) {
            try {
                // REQUEST Headers
                Headers requestHeaders = t.getRequestHeaders();
                Set<Map.Entry<String, List<String>>> entries = requestHeaders.entrySet();

                int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));

                // REQUEST Body
                InputStream is = t.getRequestBody();

                byte[] data = new byte[contentLength];
                int length = is.read(data);

                // RESPONSE Headers
                Headers responseHeaders = t.getResponseHeaders();

                // Send RESPONSE Headers
                t.sendResponseHeaders(HttpURLConnection.HTTP_OK, contentLength);

                // RESPONSE Body
                try (OutputStream os = t.getResponseBody()) {
                    os.write(data);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (t.getRequestMethod().equalsIgnoreCase("GET")) {

            Map<String, String> params = SoenServer.queryToMap(t.getRequestURI().getQuery());
            response.append("<html><body>");
            response.append("Format : " + params.get("formats") + "<br/>");
            response.append("Name : " + params.get("name") + "<br/>");
            response.append("Email : " + params.get("email") + "<br/>");
            response.append("</body></html>");

            SoenServer.sendOkResponse(t, response.toString());
        }

    }

}
