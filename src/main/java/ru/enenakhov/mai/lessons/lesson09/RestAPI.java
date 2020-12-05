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

//SOAP API
//RESTfull API
//Tomcat, WebSphere, Jetty, Apache, WildFly JBoss, Nginx
//Heroku
//Servlet
public class RestAPI {

    private static final Logger logger = Logger.getLogger(RestAPI.class.getName());
    private static final Integer PORT = 8082;

    private static final String GET = "GET";

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            HttpContext contextHello = server.createContext("/api/hello", httpExchange -> {
                if (httpExchange.getRequestMethod().equalsIgnoreCase(GET)) {
                    String query = httpExchange.getRequestURI().getQuery();
                    Map<String, String> parameters = splitParameters(query);

                    String responseText = String.format("<h1>Hello %s!</h1><h2>All parameters: %s</h2>",
                            parameters.getOrDefault("name", "noname"), parameters.toString());

                    httpExchange.sendResponseHeaders(200, responseText.getBytes().length);
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(responseText.getBytes());
                    os.flush();
                    httpExchange.close();
                } else {
                    logger.info("Запрос мы не обрабатываем: " + httpExchange.getRequestMethod());
                }
            });

            server.createContext("/api/by", httpExchange -> {
                String responseText = "<h1>BY</h1>";
                httpExchange.sendResponseHeaders(200, responseText.getBytes().length);
                OutputStream os = httpExchange.getResponseBody();
                os.write(responseText.getBytes());
                os.flush();
                httpExchange.close();
            });

            contextHello.setAuthenticator(new BasicAuthenticator("default") {
                @Override
                public boolean checkCredentials(String user, String password) {
                    return user.equals("evgenii") && password.equals("1234");
                }
            });

            server.start();
            logger.info("Rest API сервис стартовал на порту " + PORT);
        } catch (IOException ex) {
            logger.error("Проблема с веб сервером на порту " + PORT, ex);
        }

    }

    public static Map<String, String> splitParameters(String query) {
        return Pattern.compile("&")
                .splitAsStream(Optional.of(query).orElse(""))
                .map(s -> s.split("=", 2))
                .collect(Collectors.toMap(a -> a[0], a -> a[1]));
    }

}
