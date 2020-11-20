package ru.enenakhov.mai.lessons.lesson07;

public class Coords<T extends TwoD> {
    T[] coords;

    public Coords(T[] coords) {
        this.coords = coords;
    }
}
