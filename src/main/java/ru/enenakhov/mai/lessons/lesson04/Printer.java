package ru.enenakhov.mai.lessons.lesson04;

import java.time.Duration;

public class Printer {
    public synchronized void print(String message) throws InterruptedException {
        Object monitor = new Object();
        System.out.print("[");
        monitor.wait(Duration.ofSeconds(3).toMillis());
        System.out.print(message);
        monitor.wait(Duration.ofSeconds(1).toMillis());
        System.out.println("]");
    }
}
