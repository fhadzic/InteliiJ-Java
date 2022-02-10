package ba.unsa.etf.rpr.Tutorijal1;

import java.util.Scanner;

public class Zadatak {

    public static void main(String[] args) {
        System.out.println("Unesite prvi cijeli broj: ");
        Scanner ulaz = new Scanner(System.in);
        int n = ulaz.nextInt();

        System.out.println("Unesite drugi cijeli broj: ");
        int m = ulaz.nextInt();

        System.out.println("Prvi broj je " + n + ", a drugi " + m + " .");
    }
}
