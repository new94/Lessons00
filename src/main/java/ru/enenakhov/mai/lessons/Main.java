package ru.enenakhov.mai.lessons;


import org.apache.log4j.Logger;
import ru.enenakhov.mai.lessons.lesson01.AnimalAnotherInterface;
import ru.enenakhov.mai.lessons.lesson01.Cat;
import ru.enenakhov.mai.lessons.lesson01.Dog;

import java.util.ArrayList;
import java.util.List;

//https://github.com/new94/Lessons00

public class Main {

    public static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        Cat cat = Cat.build("CatName", 10.0, 2);

        logger.info(cat);
        logger.info("Cat name: " + cat.name);

        logger.info("===========");

        cat.move();

        logger.info("===========");

        List<AnimalAnotherInterface> animalList = new ArrayList();
        animalList.add(cat);
        animalList.add(new Dog());

        for (AnimalAnotherInterface animal : animalList) {
            animal.hunt();
        }

        try {
            throw new IllegalArgumentException("ERROR ARGUMENT");
        } catch (IllegalArgumentException ex) {
            logger.error("ERROR WITH MESSAGE:", ex);
        }
    }
}
