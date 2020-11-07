package ru.enenakhov.mai.lessons.lessons06;

public class ClientTwo {
    public static void main(String[] args) {
        Client client =  new Client("localhost", 8843, "Alice");
        client.start();
    }
}
