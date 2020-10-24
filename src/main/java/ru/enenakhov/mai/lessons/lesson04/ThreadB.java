package ru.enenakhov.mai.lessons.lesson04;

import java.time.Duration;

public class ThreadB extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(Duration.ofSeconds(1).toMillis());
        } catch (InterruptedException e) {
            System.err.println("ThreadB interrupted");
        }
        System.out.println("ThreadB started");
    }
}
