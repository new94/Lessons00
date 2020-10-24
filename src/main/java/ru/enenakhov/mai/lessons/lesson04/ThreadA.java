package ru.enenakhov.mai.lessons.lesson04;

import java.time.Duration;

public class ThreadA implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(Duration.ofSeconds(2).toMillis());
        } catch (InterruptedException e) {
            System.err.println("ThreadA interrupted");
        }
        System.out.println("ThreadA started");
    }
}
