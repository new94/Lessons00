package ru.enenakhov.mai.lessons.lesson06;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {

    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());

    private static final String ANCHOR_NAME = "###";

    private Socket client;
    private Server server;
    private String name;

    public ClientHandler(Socket client, Server server) {
        this.client = client;
        this.server = server;
    }

    @Override
    public void run() {
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(client.getInputStream());

            while (inputStream.hasNext()) {
                String text = inputStream.nextLine();
                logger.info("Сообщение от клиента: " + text);
                if (text.contains(ANCHOR_NAME)) {
                    name = text.substring(text.indexOf(ANCHOR_NAME) + ANCHOR_NAME.length());
                } else {
                    server.sendMessageToChat(text, name);
                }
//                PrintWriter outputStream = new PrintWriter(client.getOutputStream());
//                outputStream.println("Эхо: " + text);
//                outputStream.flush();
//                outputStream.close();

            }
        } catch (IOException e) {
            logger.error("Ошибка при работе с клиентом", e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

            if (client != null) {
                try {
                    client.close();
                } catch (IOException ex) {
                    logger.error("Ошибка при закрытии клиента!", ex);
                }
            }
        }
    }

    public void sendMessage(String message) {
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(client.getOutputStream());
            outputStream.println(message);
            outputStream.flush();
        } catch (IOException e) {
            logger.error("Проблема при записи сообщения в поток клиента: " + client.toString(), e);
        }
    }

    public String getName() {
        return name;
    }
}
