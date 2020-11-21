package ru.enenakhov.mai.lessons.lesson08;

public interface Processing<T> {
    public void process(T... message);
}
