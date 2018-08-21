package com.myApp;

import java.math.BigDecimal;
import java.util.Scanner;

public class MyScanner {
    private static Scanner sc = new Scanner(System.in);

    public static String getString() {
        return sc.nextLine();
    }

    public static int getInt() {
        return Integer.parseInt(sc.nextLine());
    }

    public static BigDecimal getBigDecimal() {
        return new BigDecimal(sc.nextLine());
    }

    public static void close() {
        sc.close();
    }
}
