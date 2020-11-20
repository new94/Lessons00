package ru.enenakhov.mai.lessons.lesson07;

public class ThreeD extends TwoD {
    private Integer z;

    public ThreeD(Integer x, Integer y, Integer z) {
        super(x, y);
        this.z = z;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }
}
