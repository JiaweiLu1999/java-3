package com.example.java3.week3;

import java.util.TreeMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class EvaluationReview {
    private String a = "";
    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
        Student s = new Student();
        TreeMap<Student, Integer> map = new TreeMap<>();
        map.put(s, 1);
    }

    private static class Student {
        int s_id;
        int id;
        int t_id;
    }
}

class MyBlockingQueue<T> {
    private Object[] arr;
    private int size;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();
    private int start, end = 0;

    public MyBlockingQueue(int capacity) {
        this.arr = new Object[capacity];
    }

    //if arr is empty => consumer cannot take element =>  consumer will wait int empty waiting list
    public T take() throws Exception{
        lock.lock();
        try {
            while(size == 0) {
                empty.await();
            }
            T res = (T)arr[start++];
            if(start == arr.length) {
                start = 0;
            }
            size--;
            full.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }

    public void put(T ele) throws Exception{
        lock.lock();
        try {
            while(size == arr.length) {
                full.await();
            }
            arr[end++] = ele;
            if(end == arr.length) {
                end = 0;
            }
            size++;
            empty.signal();
        } finally {
            lock.unlock();
        }
    }
}

/**
 *  employee (id, name, salary)
 *  1   Tom     10
 *  2   Tom     15
 *
 *
 *  select name, sum(salary)
 *  from employee
 *  group by name
 */