package ru.enenakhov.mai.lessons.lesson07;

public class Main {
    public static void main(String[] args) {
        TwoD[] twoD = new TwoD[1];
        twoD[0] = new TwoD(1,2);
        Coords<TwoD> coords2D = new Coords<>(twoD);
        showXY(coords2D);

        ThreeD[] threeD = new ThreeD[1];
        threeD[0] = new ThreeD(1,2,3);
        Coords<ThreeD> coords3D = new Coords<>(threeD);
        showXYZ(coords3D);

        FourD[] fourD = new FourD[1];
        fourD[0] = new FourD(1,2,3, 4);
        Coords<FourD> coords4D = new Coords<>(fourD);
        showXYZD(coords4D);

        twoD[0].show(1,2,3,4,"1");
    }

    public static void showXY(Coords<?> coords) {
        for (int i = 0; i != coords.coords.length; i++) {
            System.out.println(coords.coords[i].getX() + ":" + coords.coords[i].getY());
        }
    }

    public static void showXYZ(Coords<? extends ThreeD> coords) {
        for (int i = 0; i != coords.coords.length; i++) {
            System.out.println(coords.coords[i].getX() + ":" + coords.coords[i].getY() + ":" + coords.coords[i].getZ());
        }
    }

    public static void showXYZD(Coords<? extends FourD> coords) {
        for (int i = 0; i != coords.coords.length; i++) {
            System.out.println(coords.coords[i].getX() + ":" + coords.coords[i].getY() + ":" + coords.coords[i].getZ() + ":" + coords.coords[i].getD());
        }
    }
}
