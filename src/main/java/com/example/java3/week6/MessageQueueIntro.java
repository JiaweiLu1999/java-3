package com.example.java3.week6;

/**
 *     rest api get 1million rows
 *        |
 *      tomcat
 *
 *      1. user -> get -> server
 *      2. user <- ok <- server
 *      3. user <- email(url) <- server
 *      4. user download data from url
 *      *      *      *      *      *      *      *      *      *
 *
 *        user
 *          |
 *      server1  ----  ---- server2
 *          |                   |
 *         DB                  DB
 *
 *      1. user -> post -> server1
 *      2. server1 -> http -> server2
 *      3. server1 <- response <- server2
 *      4. user <- response <- server1
 *
 *
 *        user
 *          |
 *      server1  ----    message queue  ---- server2
 *          |                                   |
 *         DB                                   DB
 *
 *      1. user -> post -> server1
 *      2. server1 -> send message -> message queue
 *      3. server1  <- ack from mq  <- message queue
 *      4. user <- ok  <-  server1
 *
 *
 *      1. server2 -> pull messages from message queue
 *      2. server2 process messages
 *
 *  why message queue
 *      1. decoupled services , better user exp (users not waiting for long time)
 *      2. get more requests / scalability
 *
 *   Thread Pool
 *      producer    ->   [][][][][][][]blocking queue  ->  consumer
 *      server      ->   [][][][][][][]message queue   ->  server
 *      *      *      *      *      *      *      *      *      *
 *      queue model
 *
 *      producer1                                               consumer1  m7
 *      producer2     -> [m3][m2][m5][m1][m6][m8][m7]queue ->   consumer2  m6
 *      producer3                                               consumer3  m8
 *                                                              consumer4  m1
 *
 *      topic model / publish-subscriber model
 *
 *                                              subscriber1 m1
 *      publisher1  -> Topic [][][][m1]   ->    subscriber2 m1
 *                                              subscriber3 m1
 *
 *      *      *      *      *      *      *      *      *      *
 *      RabbitMQ  / ActiveMQ
 *
 *      producer1                                               consumer1  m7
 *      producer2     -> [m3][m2][m5][m1][m6][m8][m7]queue ->   consumer2  m6
 *      producer3                                               consumer3  m8
 *                                                              consumer4  m1
 *      *      *      *      *      *      *      *      *      *
 *      Kafka
 *                          broker1(server)
 *                           Topic1                             consumer group1
 *      p1                   partition1                         consumer1 (partition1, 2, 3)
 *      p2                   partition2
 *      p3                   partition3                         consumer group2
 *                                                              consumer1 (partition1)
 *                                                              consumer2 (partition3)
 *                                                              consumer3 (partition2)
 *                                                              consumer4
 *                                                              consumer5
 *
 *    kafka broker cluster
 *
 *          broker1 - broker2
 *              \       /
 *                broker3
 *
 *       broker1            broker2             broker3
 *       T1P1 master        T1P1 slave          T1P1 slave
 *       T1P2 slave         T1P2 slave          T1P2 master
 *       T1P3 slave         T1P3 master         T1P3 slave
 *
 *        *        *        *        *        *        *        *        *        *        *        *
 *    Discuss message issues
 *      1. duplicate messages
 *      2. transaction
 *
 *      3pmCDT
 *
 *
 */
