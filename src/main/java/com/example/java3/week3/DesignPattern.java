package com.example.java3.week3;

/**
 *  SOLID principle ?
 *  Single Responsibility
 *  Open close
 *  Liskov substitution
 *  Interface segregation
 *  Dependency inversion
 *      class A {
 *          private B b;
 *      }
 *
 *      class BImpl implements B {
 *
 *      }
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  Use case: we want one instance / object to be shared in jvm
 *  Singleton
 *
 *  issues :
 *      1. serializable
 *          obj1 -> serialize - > disk -> deserialize -> obj2
 *          obj1 == obj2 :  false
 *      2. cloneable
 *      3. reflection
 */
//eager loading
class SingletonEagerLoading implements Cloneable {
    private static final SingletonEagerLoading obj = new SingletonEagerLoading();

    private SingletonEagerLoading() {
        if(obj != null) {
            throw new RuntimeException();
        }
    }

    public static SingletonEagerLoading getInstance() {
        return obj;
    }

    @Override
    public Object clone() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        SingletonEagerLoading singletonEagerLoading1 = SingletonEagerLoading.getInstance();
        SingletonEagerLoading singletonEagerLoading2 = SingletonEagerLoading.getInstance();
        System.out.println(singletonEagerLoading1 == singletonEagerLoading2);
    }
}
//lazy loading
class SingletonLazyLoading {
    private volatile static SingletonLazyLoading obj;

    private SingletonLazyLoading() {}

    public static SingletonLazyLoading getInstance() {
        if(obj == null) {
            synchronized (SingletonLazyLoading.class) {
                if(obj == null) {
                    obj = new SingletonLazyLoading();
                }
            }
        }
        return obj;
    }
}
//Enum
enum MyEnumSingleton {
    INSTANCE1,INSTANCE2;
}

@Data
@AllArgsConstructor
@ToString
class Day12Student {
    private int id;
    private String name;
    private int age;
    private Date createdDate;
    //...

    public Day12Student() {
    }

    public Day12Student(int id) {
        this.id = id;
    }

    public Day12Student setId(int id) {
        this.id = id;
        return this;
    }
}
class ReflectionExample {
    public static void main(String[] args) throws Exception {
//        Class<?> clazz = SingletonEagerLoading.class;
//        Constructor constructor = clazz.getDeclaredConstructors()[0];
//        constructor.setAccessible(true);
//        SingletonEagerLoading instance1 = (SingletonEagerLoading) constructor.newInstance();
//        System.out.println(instance1);
//        SingletonEagerLoading instance2 = SingletonEagerLoading.getInstance();
//        System.out.println(instance2 == instance1);

        Day12Student student = new Day12Student();
        System.out.println(student);
        Class<Day12Student> clazz = Day12Student.class;
        Field f = clazz.getDeclaredFields()[0];
        f.setAccessible(true);
        f.set(student, 5);
        System.out.println(student);

        Method m = clazz.getMethod("setId", int.class);
        m.invoke(student, 10);
        System.out.println(student);
    }
}


/**
 * Factory (loose coupling / hide initialization)
 *  1. abstract factory
 *  2. factory method
 *  ..
 */

abstract class CarFactory {
}
class BMWCarFactory extends CarFactory {}

class StudentFactory {
    private StudentFactory() {}

    public static Day12Student getStudent() {
        return new Day12Student();
    }
    public static Day12Student getStudent(int id) {
        return new Day12Student(id);
    }
}

/**
 *  Builder (dynamic initialization)
 */
class Day12StudentBuilder {
    private int id;
    private String name;
    private int age;
    private Date createdDate;
    //..


    public Day12StudentBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public Day12StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Day12StudentBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public Day12StudentBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Day12Student build() {
        return new Day12Student(id, name, age, createdDate);
    }
}
class BuilderTest {
    public static void main(String[] args) {
        Day12Student s1 = new Day12StudentBuilder().setName("Tom").setAge(5).setId(4).build();
        System.out.println(s1);
    }
}

/**
 *  prototype
 *      getInstance() => get new instance
 *      clone()
 *      deep copy / shallow
 */

/**
 *  observer (publisher + Topic + subscriber)
 */
class Subscriber {
    public void receive(String msg) {
        System.out.println(msg);
    }
}

class Topic {
    private final List<Subscriber> subscriberList = new ArrayList<>();

    public void subscribe(Subscriber sub) {
        subscriberList.add(sub);
    }

    public void publish(String msg) {
        subscriberList.forEach(s -> s.receive(msg));
    }
}


/**
 *  template
 */
abstract class CarTemplate {
    public void start() {
        System.out.println("start");
    }
    public void stop() {
        System.out.println("stop");
    }
}
class BMWCar extends CarTemplate {}
class HondaCar extends CarTemplate {}


/**
 *  composition(Has-A)
 *  class TreeNode {
 *      TreeNode left;
 *      TreeNode right;
 *  }
 *
 *  aggregation(Is-A)
 *  inheritance,  extends
 */

/**
 *  bridge
 */
class Day12ABridge {
    private B b;

    public Day12ABridge(B b) {
        this.b = b;
    }

    public void print() {
        b.print();
    }
}

interface B {
    void print();
}
class BImpl1 implements B {
    @Override
    public void print() {
        System.out.println("printing from b impl 1");
    }
}
/**
 *  strategy
 */
class Day12AStrategy {
    public void print(B b) {
        b.print();
    }

    public static void main(String[] args) {
        new Day12AStrategy().print(() -> System.out.println("my customized impl"));
        new Day12AStrategy().print(new BImpl1());
    }
}

/**
 *  facade pattern (API gateway)
 *
 *              |
 *          front door(apt info / company location info)  -> rate limiter + log info
 *          /   \       \
 *       apt1   apt2    apt3
 *
 *
 *          class XX {
 *              public void handle(String msg) {
 *                  switch(msg) {
 *                      case "x1" :
 *                          apt1.process(msg);
 *                          break;
 *                      case "x2" :
 *                          ..
 *                      default :
 *                          ..
 *                  }
 *              }
 *          }
 */

/**
 *  install a RDBMS database : postgre / mysql / oracle
 *  Leet code : at least 1h / day
 *      1. bug free easy level question in 5min on whiteboard
 *      2. common medium questions
 *          BFS / DFS
 *              BFS : print node level by level
 *              DFS : count islands / tree
 *          two pointers + binary search
 *          sliding window
 *          heap : top k elements
 *          hashmap : two sum
 *          ..
 *
 *   this afternoon 2pm cdt
 *
 *
 *
 */



/**
 *  adaptor
 *  decorator (static proxy)
 *  dynamic proxy
 */
