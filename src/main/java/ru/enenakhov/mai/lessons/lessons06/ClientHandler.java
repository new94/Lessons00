package ru.enenakhov.mai.lessons.lessons06;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {

    public static final Logger logger = Logger.getLogger(ClientHandler.class.getName());

    private Socket client;
    private Server server;
    private String name;
    private String address;

    public ClientHandler(Socket client, Server server) {
        this.client = client;
        this.server = server;
    }

    @Override
    public void run() {
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(client.getInputStream());

//            PrintWriter outputStream = new PrintWriter(client.getOutputStream());
            while (inputStream.hasNext()) {
                String text = inputStream.nextLine();
                if (text.contains("###")) {
                    name = text.substring(text.indexOf("###") + 3);
                    address = client.toString();
                }
                logger.info(text);
//                outputStream.println("Answer " + text);
//                outputStream.flush();
                server.sendMessageToChat(text, name);
            }
        } catch (IOException e) {
            logger.error("Проблема с обработкой сообщений клиента", e);
        }
    }

    public void sendMessage(String text) {
        try {
            PrintWriter outputStream = new PrintWriter(client.getOutputStream());
            outputStream.println(text);
            outputStream.flush();
        } catch (IOException e) {
            logger.error("Ошибка при отправке сообщения в чат!", e);
        }
    }

    public Socket getClient() {
        return client;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
