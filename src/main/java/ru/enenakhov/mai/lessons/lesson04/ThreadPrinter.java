package ru.enenakhov.mai.lessons.lesson04;

public class ThreadPrinter implements Runnable {

    private final Printer printer;
    private final String message;

    public ThreadPrinter(Printer printer, String message) {
        this.printer = printer;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            printer.print(message);
        } catch (InterruptedException e) {
            System.err.println("Interrupted Thread one printer");
        }
    }
}
