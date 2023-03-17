package com.example.homework.day13.aop.example;

class StudentServiceImpl implements StudentService {
    @Override
    public void print() {
        System.out.println("this is print");
    }


    @Override
    public int get() {
        System.out.println("this is get");
        return 0;
    }

    @Override
    public void set() {
        System.out.println("this is set");
    }

    @Override
    public void error() {
        throw new RuntimeException();
    }
}
