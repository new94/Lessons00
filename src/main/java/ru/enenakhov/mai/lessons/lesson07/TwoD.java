package ru.enenakhov.mai.lessons.lesson07;

public class TwoD<T> {
    private Integer x;
    private Integer y;

    public TwoD(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void show(Object... arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.println(arr[i].toString());
        }
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
