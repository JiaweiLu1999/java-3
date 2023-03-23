package com.example.java3.week4;

/**
 * What is server?
 *      blocking(tomcat)
 *          -> requests -> application(destination ip + destination port)(ThreadPool(1000 threads) + waiting list(1000))
 *                          1. socket => build connection[source ip + source port, destination ip + destination port]
 *                              socket.accept() => create a new socket => assigned to new thread
 *                          2. assign connection to thread  -> handle your request -> service -> repo -> database
 *                          3. return response to you
 *      non-blocking(netty)
 *          -> requests -> application(destination ip + destination port)(ThreadPool(1000 threads) + event loop)
 *                          [][][][]event loop[][][][][] -> thread pool
 * Spring (2 servers in spring)
 *      1. by default => blocking(tomcat)
 *      2. webflux(netty)
 */