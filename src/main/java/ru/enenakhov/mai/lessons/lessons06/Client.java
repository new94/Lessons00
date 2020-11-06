package ru.enenakhov.mai.lessons.lessons06;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static final Logger logger = Logger.getLogger(Client.class.getName());

    private String name;

    public Client(String name) {
        this.name = name;
    }

    public void start() {
        try {
            Socket server = new Socket("localhost", 8843);
            PrintWriter outputStream = new PrintWriter(server.getOutputStream());
            outputStream.println("Привет! Меня зовут ###" + name);
            outputStream.flush();
            Scanner inputStream = new Scanner(server.getInputStream());

            new Thread(() -> {
                while (inputStream.hasNext()) {
                    logger.info(inputStream.nextLine());
                }
            }).start();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                if (scanner.hasNext()) {
                    String text = scanner.nextLine();
                    outputStream.println(text);
                    outputStream.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}
