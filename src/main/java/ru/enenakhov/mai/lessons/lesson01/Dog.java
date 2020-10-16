package ru.enenakhov.mai.lessons.lesson01;

import org.apache.log4j.Logger;

public class Dog extends Animal implements AnimalInterface, AnimalAnotherInterface {

    private static final Logger logger = Logger.getLogger(Dog.class.getName());

    @Override
    public void say() {
        logger.info("Woof!");
    }

    @Override
    public void seek() {
        logger.info("Dog seek");
    }

    @Override
    public void hunt() {
        logger.info("Dog hunt");
    }
}
