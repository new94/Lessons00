package ru.enenakhov.mai.lessons.lessons03;

import org.apache.log4j.Logger;

public class Exceptions {

    private static final Logger logger = Logger.getLogger(Exceptions.class.getName());

    public static void main(String[] args) {
        String a = null;
        try {
            a.hashCode();
        } catch (Exception ex) {
            logger.error("CATCH EXCEPTION", ex);
        } catch (Throwable ex) {
            logger.warn("CATCH WARNING");
        } finally {
            logger.warn("WARNING!!! CATCH EXCEPTION!");
        }
    }

}
