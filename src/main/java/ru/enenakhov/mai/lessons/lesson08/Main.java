package ru.enenakhov.mai.lessons.lesson08;

public class Main {
    public static void main(String[] args) {
        ClassA<Integer> classA = new ClassA<>();
        classA.setFunction((message) -> System.out.println(message.length));
        classA.process(10,1,2,5,6);

        classA.setFunction((message -> System.out.println(message[0])));
        classA.process(2,4);
    }
}
