package com.example.java3.week5;


// java Test 17
public class ReadJavaCode {
    static Integer I;
    public static void main(String[] args) {

    }

    public static void func1(String[] args) {
        String s;
        try {
            s = I.toString();
        } finally {
            try {
                int i = Integer.parseInt(args[0]);
                System.out.println(i);
            } catch (Exception ex) {
                System.out.println("ex");
            } finally {
                System.out.println("fin2");
            }
            System.out.println("fin1");
        }
    }

    public static void func2() {
        System.out.println("" + 1 + 2);
    }

    public static void func3() {
        System.out.println("" + (1 + 2));
    }
}