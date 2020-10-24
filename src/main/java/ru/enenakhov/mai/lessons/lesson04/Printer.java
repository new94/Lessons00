package ru.enenakhov.mai.lessons.lesson04;

import java.time.Duration;

public class Printer {
    public synchronized void print(String message) throws InterruptedException {
        System.out.print("[");
        Thread.sleep(Duration.ofSeconds(3).toMillis());
        System.out.print(message);
        Thread.sleep(Duration.ofSeconds(1).toMillis());
        System.out.println("]");
    }
}
