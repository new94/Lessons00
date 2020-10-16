package ru.enenakhov.mai.lessons.lesson01;

import org.apache.log4j.Logger;

import java.util.Objects;

public class Cat extends Animal implements AnimalInterface, AnimalAnotherInterface {

    private static final Logger logger = Logger.getLogger(Cat.class.getName());

    public String name;
    public Double weight;
    private Integer age;

    private Cat() {
        name = "Cat";
        weight = 1.5;
        age = 1;
    }

    private Cat(String name, Double weight, Integer age) {
        this.name = name;
        this.weight = weight;
        this.age = age;
    }

    public static Cat build(String name, Double height, Integer age) {
        logger.info("Invoke method build to create Cat");
        logger.info("To do something...");
        Double weight = height / age;
        if (weight > 1) {
            return new Cat();
        } else {
            return new Cat(name, weight, age);
        }
    }

    public void move() {
        MoveCat mover = new MoveCat();
        mover.jump();
        mover.run();
        logger.info("Actions: " + mover.getActions());
    }

    @Override
    public void say() {
        super.say();
        logger.info("Meow!");
    }

    @Override
    public void seek() {
        logger.info("Cat seek");
    }

    @Override
    public void hunt() {
        logger.info("Cat hunt");
    }

    class MoveCat {
        private Integer action = 0;

        public void jump() {
            action += 1;
            logger.info("Cat jump");
        }

        public void run() {
            action += 1;
            logger.info("Cat run");
        }

        public Integer getActions() {
            return action;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(name, cat.name) &&
                Objects.equals(weight, cat.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                '}';
    }
}
