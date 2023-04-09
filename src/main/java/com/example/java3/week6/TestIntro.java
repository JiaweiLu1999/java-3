package com.example.java3.week6;


import java.util.Arrays;
import java.util.List;

/**
 *  unit test
 *      service -call> (mock)repository
 *      service <-dummy data- repo
 *  integration test
 *      postman(tool)
 *      mockMVC(library, java)
 *  function test
 *  automation test
 *      selenium(framework, java)
 *  performance test
 *      jmeter(tool)
 *  security test
 *      code security scan tools
 *      ..
 *  smoke / regression test
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   What will you do after you get stories?
 *      1. analyze stories
 *      2. clarification -> BA/QA/Manager corner cases
 *      3. OOD design / feature design
 *          check out feature branch
 *      4. Abstract class / interface / Class Impl + //TODO
 *      5. write Test cases for each methods / diff methods
 *      6. finish //TODO
 *      7. run test cases
 *          commit push, pull request code review -> merge to development branch
 *      ...
 *
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   master branch      -----                                                               prod env
 *                          / v1.0.0
 *   release branch     ---------------------------------------------------------------     uat env
 *                                      / v1.0.1                    / v1.0.2
 *   development branch ---------------------------------------------------------------     dev env
 *                          \               / pull request code review + merge
 *   feature branch          --------------                                                 local env
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   what to check in code review
 *      1. naming style / comment / documentation
 *      2. business logic
 *      3. reuse code / use utilities / features from existing implementation
 *      4. time complexity  / space complexity
 *      5. OOD / SOLID
 *      6. log / exception
 *      ..
 *
 *
 *  class MyBean<T> {
 *
 * 	    private String a1;
 * 	    private String a2;
 * 	    private T a3;
 *
 * 	    //equals
 * 	    //toString
 * 	    //getter setter constructor
 * }
 *
 *  //need hashcode
 *  //naming style
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *     class MyArrayList {
 *
 *     }
 *
 */

class MyArrayList {
    private Object[] arr;

    public Object get(int idx) {
        //TODO
        return null;
    }

    public boolean remove(int idx) {
        //TODO
        return false;
    }

    public void add(Object obj) {
        //TODO
    }

    public int size() {
        //TODO
        return 0;
    }
}


//impl1
class MyArrayList1 {
    private Object[] arr;
    private int size;

    public Object get(int idx) {
        if(arr == null || idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException();
        }
        return arr[idx];
    }

    public boolean remove(int idx) {
        // return true if removed, false if not
        if (idx < 0 || idx >= size) {
            //return false;
            throw new IndexOutOfBoundsException();
        }
//        for (int i = 0; i < arr.length; i++) {
//            if (i == idx) {
//                for (int j = i; j < arr.length - 1; j++) {
//                    arr[j] = arr[j + 1];
//                }
//                arr[arr.length - 1] = null;
//                arr = Arrays.copyOf(arr, arr.length - 1);
//                return true;
//            }
//        }
        for (int j = idx; j < size - 1; j++) {
            arr[j] = arr[j + 1];
        }
        arr[size - 1] = null;
        size--;
        return true;
    }

    //O(N)
    public void add(Object obj) {
        if (arr == null) {
            arr = new Object[8];
            arr[0] = obj;
        } else {
//            arr = Arrays.copyOf(arr, arr.length  1);
//            arr[arr.length - 1] = obj;

            //if no empty slot -> resize arr.length * 2
            //add element to arr[size]
        }
        size++;
    }

    public int size() {
        return size;
//        int count = 0;
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] != null) {
//                count++;
//            }
//        }
//        return count;
    }

    public static void main(String[] args) {
        MyArrayList1 l = new MyArrayList1();
        l.get(0); // null ?
    }
}


//impl2
class MyArrayList2 {
    private Object[] arr;
    private int size;

    public MyArrayList2() {
        this(8);
    }
    public MyArrayList2(int capacity) {
        arr = new Object[capacity];
    }

    public Object get(int idx) {
        //idx < 0
        if (idx < size) {
            return arr[idx];
        }
        //index out of exception
        return null;
    }

    public boolean remove(int idx) {
        //idx < 0
        if (idx < size) {
            for (int i = idx; i < size - 1; i++) {
                arr[i] = arr[i + 1];
            }
            size--;
            return true;
        }
        return false;
    }

    public void add(Object obj) {
        if (size == arr.length) {
            expand();
        }
        arr[size++] = obj;
    }

    public int size() {
        return size;
    }

    private void expand() {
        //overflow => int
        arr = Arrays.copyOf(arr, size + size / 2);
    }
}


//impl3
class MyArrayList3 {
    private Object[] arr;
    private int size;

    public MyArrayList3() {
        this.arr = new Object[10];
    }

    public Object get(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException();
        }
        return arr[idx];
    }

    public boolean remove(int idx) {
        if (idx < 0 || idx >= size) {
            return false;
        }
        for (int i = idx; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[size - 1] = null;
        size--;
        return true;
    }

    public void add(Object obj) {
        if (size == arr.length) {
            Object[] newArr = new Object[arr.length * 2];
            System.arraycopy(arr, 0, newArr, 0, size);
            arr = newArr;
        }
        arr[size] = obj;
        size++;
    }

    public int size() {
        return size;
    }
}

//impl4
class MyArrayLis4 {
    //forget to initialize arr
    private Object[] arr;

    public Object get(int idx) {
        if (idx - 1 < 0 || idx - 1 >= arr.length) {
            return null;
        } else {
            return arr[idx];
        }
    }

//    public boolean remove(int idx) {
//        if (idx - 1 < 0 || idx - 1 >= arr.length) {
//            return false;
//        } else {
//            arr[idx] = null;
//            return true;
//        }
//    }

    //not following arraylist implementation
    public void add(Object obj) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                arr[i] = obj;
                return;
            }

            int idx = arr.length;

            Object[] newArr = new Object[arr.length * 2];
            for (int j = 0; j < arr.length; j++) {
                newArr[i] = arr[j];
            }
            newArr[idx] = obj;
            arr = newArr.clone();
        }
    }

    //size is not count of elements
    public int size() {
        return arr.length;
    }

    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        list.add("a");
        list.add(5);
        list.add(false);
    }
}