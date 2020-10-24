package ru.enenakhov.mai.lessons.lesson04;

import java.time.Duration;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Threads {
    public static void main(String[] args) {

        try {
            ThreadA runnerA = new ThreadA();
            Thread threadA = new Thread(runnerA);
//        threadA.start();

            ThreadB threadB = new ThreadB();
//        threadB.start();

            Runnable runnerC = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Duration.ofSeconds(3).toMillis());
                    } catch (InterruptedException e) {
                        System.err.println("ThreadC interrupted");
                    }
                    System.out.println("ThreadC started");
                }
            };

            Thread threadC = new Thread(runnerC);
//        threadC.start();

            Runnable runnerD = () -> {
                System.out.println("ThreadD started");
            };
            Thread threadD = new Thread(runnerD);
            threadD.start();
            threadD.join();

//        new Thread(() -> {
//            System.out.println("ThreadE started");
//        }).start();

//            List<Thread> listThreads = Arrays.asList(threadA, threadB, threadC, threadD, new Thread(() -> {
//                System.out.println("ThreadE started");
//            }));
//
//            System.out.println("TODO Something");
//
//            for (Thread thread : listThreads) {
//                thread.start();
//                thread.join();
//            }


//            Printer printer = new Printer();
//            ThreadPrinter runnerPrinterOne = new ThreadPrinter(printer, "Hello I'm first");
//            ThreadPrinter runnerPrinterTwo = new ThreadPrinter(printer, "Hello I'm second");
//            Thread threadPrinterOne = new Thread(runnerPrinterOne);
//            Thread threadPrinterTwo = new Thread(runnerPrinterTwo);
//            threadPrinterOne.start();
//            threadPrinterTwo.start();

            Callable<String> callThreadName = () -> {
                Thread.sleep(Duration.ofSeconds(10).toMillis());
                return Thread.currentThread().getName();
            };

            ExecutorService executorService = Executors.newCachedThreadPool();
            Future<String> futureThreadName = executorService.submit(callThreadName);

            System.out.println("Another action");

            System.out.println("GET NAME: " + futureThreadName.get());

            System.out.println("Main thread end!");

            executorService.shutdown();

            Semaphore semaphore = new Semaphore(1);
            CountDownLatch cdl = new CountDownLatch(5);
            cdl.await();
            cdl.countDown();

            Phaser phaser = new Phaser();
            Exchanger<String> exchanger = new Exchanger<>();
            String a = exchanger.exchange("B");
            String b = exchanger.exchange("A");
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        } catch (ExecutionException e) {
            System.err.println("Thread get name error: " + e.getMessage());
        }
    }
}
