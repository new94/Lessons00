package ru.enenakhov.mai.lessons.lessons06;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final Logger logger = Logger.getLogger(Server.class.getName());

    private List<ClientHandler> clients = new ArrayList<>();

    public void start() {
        try {
            ServerSocket server = new ServerSocket(8843);
            logger.info("Сервер стартовал и ждет клиентов!");
            while (true) {
                Socket client = server.accept();
                logger.info("Новый клиент " + client.toString());
                ClientHandler clientHandler = new ClientHandler(client, this);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            logger.error("Ошибка на сервере", e);
        }
    }

    public void sendMessageToChat(String message, String name) {
        for (ClientHandler client : clients) {
            logger.info("Отправка сообщения клиенту " + client.getName());
            client.sendMessage(name + ": " + message);
        }
    }

}
