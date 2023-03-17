package com.example.java3.week3.aop.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class StudentServiceImpl implements StudentService {
    @Override
    public void print() {
//        System.out.println("this is print");
        throw new RuntimeException();
    }


    @Override
    public int get() {
        throw new RuntimeException();
    }

    @Override
    public void set() {
        System.out.println("this is set");
    }

}