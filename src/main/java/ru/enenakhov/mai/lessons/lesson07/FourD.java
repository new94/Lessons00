package ru.enenakhov.mai.lessons.lesson07;

public class FourD extends ThreeD {
    private Integer d;

    public FourD(Integer x, Integer y, Integer z, Integer d) {
        super(x, y, z);
        this.d = d;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }
}
