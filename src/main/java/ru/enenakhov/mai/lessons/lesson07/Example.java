package ru.enenakhov.mai.lessons.lesson07;

public class Example<K extends Integer, T extends Number, V extends Number & GenericInterface<K>> {
    T obj;
    V obj2;

    public Example(T obj, V obj2) {
        this.obj = obj;
        this.obj2 = obj2;
    }
}
