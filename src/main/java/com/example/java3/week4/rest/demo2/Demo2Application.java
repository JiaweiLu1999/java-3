package com.example.java3.week4.rest.demo2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootApplication
public class Demo2Application {
    public static void main(String[] args) {
//        RestTemplate r = new RestTemplate();
//        String s = r.getForObject("https://api.publicapis.org/entries", String.class);
//        System.out.println(s);
        SpringApplication.run(Demo2Application.class, args);
    }
}


/**
 *  RestTemplate (send http request)
 *      1. getForObject(String url, A.class)
 *      2. {
 *              "k1": "xx",
 *              "k2": {
 *                  "k3":xx
 *              }
 *          }
 *
 *         class A {
 *             private String k1;
 *             private B k2;
 *         }
 *
 *         class B {
 *             private String k3
 *         }
 *
 *  requirement :
 *      1. write api to retrieve all entries from "https://api.publicapis.org/entries"
 *      2. write api to retrieve entries which contain "Auth": "apiKey"
 *  reference :
 *      https://stackoverflow.com/questions/15853035/create-two-method-for-same-url-pattern-with-different-arguments
 *  45min
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  What to change ?
 *      1. log
 *      2. exception
 *      3. test cases
 *      4. api document
 *
 *      5. cache
 *          a. that api always returns same data
 *              cache (hashmap)
 *          b. 3rd party api refreshes every 60s
 *              scheduler -> send request to api every 60s
 *      6. save data to database
 *      7. 10k ~ 1m entries
 *          a. /entry, get => pagination
 *          b. send link / email to user
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  2pm cdt
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Monday, Security
 *  Tuesday, code review + Microservice Introduction
 *  Wednesday, Spring Cloud framework
 *  Thursday, Database cluster, nosql
 *  Friday, Message queue
 */

