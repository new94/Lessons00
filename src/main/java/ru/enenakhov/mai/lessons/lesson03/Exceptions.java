package ru.enenakhov.mai.lessons.lesson03;

import org.apache.log4j.Logger;

public class Exceptions {

    private static final Logger logger = Logger.getLogger(Exceptions.class.getName());

    public static void main(String[] args) {
        String a = null;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            logger.error("CATCH EXCEPTION", ex);
        } catch (Throwable ex) {
            logger.warn("CATCH WARNING");
        } finally {
            logger.warn("WARNING!!! CATCH EXCEPTION!");
        }
    }

}
