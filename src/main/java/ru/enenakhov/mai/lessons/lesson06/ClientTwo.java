package ru.enenakhov.mai.lessons.lesson06;

public class ClientTwo {
    public static void main(String[] args) {
        Client client =  new Client("localhost", 8843, "Alice");
        client.start();
    }
}
