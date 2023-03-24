package com.example.java3.week4.rest.demo2;



public class Demo2Application {
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
 */