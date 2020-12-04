package ru.enenakhov.mai.lessons.lesson09;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static ru.enenakhov.mai.lessons.lesson09.HttpMethodsDictionary.GET;

public class RestAPI {

    private static final Logger logger = Logger.getLogger(RestAPI.class.getName());

    private static final Integer PORT = 8000;

    public static void main(String[] args) {
        try {
            HttpServer webServer = HttpServer.create(new InetSocketAddress(PORT), 0);
            HttpContext context = webServer.createContext("/api/hello", httpExchange -> {
                if (httpExchange.getRequestMethod().equalsIgnoreCase(GET)) {
                    String query = httpExchange.getRequestURI().getQuery();
                    Map<String, String> parameters = splitParameters(query);

                    String responseText = String.format("<h1>Hello %s!!!</h1>",
                            parameters.getOrDefault("name", "noname"));

                    responseText += String.format("<body>%s</body>", parameters.toString());

                    httpExchange.sendResponseHeaders(200, responseText.getBytes().length);
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(responseText.getBytes());
                    os.flush();
                    httpExchange.close();
                } else {
                    httpExchange.sendResponseHeaders(405, -1);
                }
            });

            context.setAuthenticator(new BasicAuthenticator("default") {
                @Override
                public boolean checkCredentials(String user, String password) {
                    return user.equals("evgenii") && password.equals("1234");
                }
            });

            webServer.createContext("/api/by", httpExchange -> {
                Map<String, String> parameters = splitParameters(httpExchange.getRequestURI().getQuery());
                String response = String.format("I'll be back %s", parameters.toString());
                httpExchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes());
                os.flush();
                httpExchange.close();
            });

            webServer.start();
            logger.info("RestAPI server started on port: " + PORT);
        } catch (IOException ex) {
            logger.error("Ошибка при запуске веб сервера на порту: " + PORT, ex);
        }
    }

    public static Map<String, String> splitParameters(String query) {
        return Pattern.compile("&")
                .splitAsStream(Optional.of(query).orElse(""))
                .map(s -> s.split("=", 2))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
    }
}
