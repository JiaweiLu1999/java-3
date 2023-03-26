package com.example.homework.day15;

import com.example.java3.week4.orm.Student;
import com.example.java3.week4.orm.Teacher;
import com.example.java3.week4.orm.Teacher_Student;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 *  relation
 *      1 database connection - 1 datasource - 1 session factory - m session
 *  ----------------------------------------------------------------------
 *  diff lazy loading vs eager loading
 *      lazy loading : select s from Student s where id = 17
 *      eager loading : select * from Student s join s.teacher_student where id = 17
 *  eager loading issues
 *      waste resources / spaces / times
 *  lazy loading issues (N + 1 issue)
 *      List<Student> sList = em.find(Student.class);  -> 1 query
 *      for(Student s: sList) { -> N queries
 *          List<Teacher_Student> ts = s.getTeacher_Student();
 *          System.out.println(ts);
 *      }
 *      how many queries we send to db
 *      N + 1
 *      how to solve it?
 *      fetch
 *  ----------------------------------------------------------------------
 *  Hibernate vs JPA(standard) vs Spring Data JPA(implementation of JPA, is using hibernate)
 *  Hibernate : session factory(2nd level cache) -> session(first level cache) -> save() / save or update()
 *  JPA       : entity manager factory -> entity -> persist() / merge()
 *
 *  persist status(new instance)  -  attach / proxy status(objects managed by persistent context) -  detach / un proxy(throw LazyInitializationException when you get relational data / query)
 *
 *  ----------------------------------------------------------------------
 *  Spring data jpa
 *  interface JpaRepository<T, K> {
 *      void save(T t);
 *      List<T> findAll();
 *      T findById(T t, K id);
 *  }
 *  class SimpleJpaRepository implements JpaRepository<T, K> {f
 *      //
 *  }
 *
 *  interface StudentRepository extends JpaRepository<Student, String> {}
 *  interface TeacherRepository extends JpaRepository<Teacher, String> {}
 *
 *  StudentRepository repository = factory.getStudentRepository();
 *  List<Student> students = repository.findAll();
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  homework:
 *      1. write 1 - m & m - 1 classes on whiteboard
 *      2. run this project in your laptop
 *          a. modify datasource
 *          b. add dependencies(database dependency) in pom.xml
 *          c. modify dialect, driver
 *
 *          search google => mysql maven dependency
 */

public class ORMConfig {

    private DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setDatabaseName("OrmDemo");
        dataSource.setUser("root");
        dataSource.setPassword("ljw13511579112");
        dataSource.setUrl("");
        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("hibernate.show_sql", "true");
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com/example/java3/week4/orm");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(hibernateProperties);
        em.setPersistenceUnitName("demo-unit");
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }

    public static void main(String[] args) {
        ORMConfig ormConfig = new ORMConfig();
        DataSource dataSource = ormConfig.getDataSource();
        Properties properties = ormConfig.getProperties();
        EntityManagerFactory entityManagerFactory = ormConfig.entityManagerFactory(dataSource, properties);

        EntityManager em = entityManagerFactory.createEntityManager();
        PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();

        Student student = em.find(Student.class, "1");

        System.out.println(student);

    }



    private static void insertToStudent(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Student s = new Student();
        s.setName("JerryTest");
        s.setId("50");
        em.merge(s);
        tx.commit();
    }

    private static void getStudentById(EntityManager em) {
        Query query = em.createQuery("select s from Student s where s.id = ?1");
        query.setParameter(1, "17");
        Student s = (Student)query.getSingleResult();
        System.out.println(s);
    }

    private static void addToJunctionTable1(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Student s = new Student();
        s.setName("30th stu");
        Teacher t = new Teacher();
        //persist t first to get new id
        em.persist(t);
        t.setName("30th teacher");
        //build connection between t and s
        Teacher_Student ts = new Teacher_Student();
        ts.setStu(s);
        ts.setTeacher(t);
        t.addTeacher_students(ts);
        em.persist(s);
        tx.commit();
    }

    private static void addToJunctionTable2(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createNativeQuery("INSERT INTO TEACHER_STUDENT (S_ID, T_ID) VALUES (?, ?)");
        query.setParameter(1, 4);
        query.setParameter(2, 4);
        query.executeUpdate();
        tx.commit();
    }

    private static void addToJunctionTable3(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Student s = em.find(Student.class, "14");
        Teacher t = em.find(Teacher.class, "9");
        Teacher_Student ts = new Teacher_Student();
        ts.setStu(s);
        ts.setTeacher(t);
        em.persist(ts);
        tx.commit();
    }

    private static void addToJunctionTable4(EntityManager em) {
        em.getTransaction().begin();
        Student s = new Student();
        s.setId("14");
        Teacher t = new Teacher();
        t.setId("12");
        Teacher_Student ts = new Teacher_Student();
        ts.setStu(s);
        ts.setTeacher(t);
        em.persist(ts);
        em.getTransaction().commit();
    }

    private static void notOrphanRemoveBiRelation(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select s from Student s join fetch s.teacher_students ts where s.id = ?1");
        query.setParameter(1, "5");
        Student s = (Student) query.getSingleResult();
        Teacher t = em.find(Teacher.class, "3");
        List<Teacher_Student> teacher_students = new ArrayList<>();
        for(Teacher_Student ts: s.getTeacher_students()) {
            if(ts.getTeacher().getId().equals(t.getId())) {
                teacher_students.add(ts);
                em.remove(ts);
            }
        }
        s.getTeacher_students().removeAll(teacher_students);
        t.getTeacher_students().removeAll(teacher_students);
        tx.commit();
    }

    private static void notOrphanRemoveSingleRelation(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select s from Student s join fetch s.teacher_students ts where s.id = ?1");
        query.setParameter(1, "5");
        Student s = (Student) query.getSingleResult();
        for(Teacher_Student ts: s.getTeacher_students()) {
            em.remove(ts);
        }
        s.setTeacher_students(new ArrayList<>());
        tx.commit();
    }

    private static void orphanRemove(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select s from Student s join s.teacher_students ts where s.id = ?1");
        query.setParameter(1, "14");
        Student s = (Student) query.getSingleResult();
        Iterator<Teacher_Student> itr = s.getTeacher_students().iterator();
        while(itr.hasNext()) {
            Teacher_Student ts = itr.next();
            if(ts.getTeacher().getId().equals("9")) {
                itr.remove();
            }
        }
        tx.commit();
    }


    private static void withoutOrphanRemove(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select s from Student s join fetch s.teacher_students ts where s.id = ?1");
        query.setParameter(1, "14");
        Student s = (Student) query.getSingleResult();
        Iterator<Teacher_Student> itr = s.getTeacher_students().iterator();
        while(itr.hasNext()) {
            Teacher_Student ts = itr.next();
            if(ts.getTeacher().getId().equals("9")) {
                itr.remove();
                em.remove(ts);
            }
        }
        tx.commit();
    }
}
