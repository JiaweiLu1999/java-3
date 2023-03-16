package com.example.java3.week3;


import java.lang.annotation.*;
import java.util.Arrays;

/**
 * annotations
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Component {
    String[] value() default {"abc", "ss"};
}

@Component(value = {"one", "two"})
class Day13TeacherService {

    private String a = "aaa";

    public Day13TeacherService() {
    }

    public void get() {}
}

class AnnotationTest {
    public static void main(String[] args) {
        Class<Day13TeacherService> clazz = Day13TeacherService.class;
        Component annotation = (Component)clazz.getDeclaredAnnotations()[0];
        System.out.println(Arrays.toString(annotation.value()));
    }
}
/**
 *  main()
 *  1. scan all classes
 *  2. get annotations from current class
 *      if annotation == Component.class
 *          xxx
 *      else if annotation == xx.class
 *          xxx
 *
 */



