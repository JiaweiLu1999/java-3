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
 *   message queue duplicate message
 *      1. idempotent service
 *          put -> update table set name = Tom where id = xx and name = Jerry
 *          post -> insert into table values (id, )
 *      2. global cache / save id of processed messages
 *        *        *        *        *        *        *        *        *        *        *        *
 *   global transaction problem1 (message queue + database)
 *         user
 *          |
 *         service1     -   message queue
 *          |
 *         DB
 *
 *         service1
 *         1. send msg to message queue
 *         2. save data into DB
 *
 *          change data capture + out box pattern
 *
 *     solution  : CDC + outbox
 *         user
 *          |
 *         service1
 *          |
 *         DB     -   CDC service  -  message queue   -   service2
 *         1. service1
 *              begin tx
 *              insert user's data
 *              insert message into outbox table
 *              commit tx / rollback when any exception happens
 *
 *         2. CDC service monitoring outbox table
 *              read message
 *              send to message queue
 *              delete message
 *        *        *        *        *        *        *        *        *        *        *        *
 *   global transaction problem2 (database  + database)
 *
 *                  service
 *                 /        \
 *               db1        db2
 *          1. insert into db1
 *          2. insert into db2
 *
 *          begin db1 tx
 *          insert into db1
 *          begin db2 tx
 *          insert into db2
 *          commit db1
 *          commit db2
 *
 *
 *      solution 1 : 2pc / two phase commit
 *
 *               request
 *                  |
 *              coordinator
 *             /            \
 *         db1              db2
 *
 *           step1: send query to database,
 *                  execute the query
 *                  save everything into local log(disk)
 *                  need ok response from db1, db2
 *           step2: send commit commands to both databases
 *      solution 2 : 3pc / three phase commit
 *      solution 3 : SAGA pattern
 *
 *           flight service  <->   message queue  <->   hotel service <-> message queue  <->  car rental service
 *                 |                                    |                                       |
 *               DB                                     DB                                      DB
 *
 *        book flight in flight service -> if fail -> rollback , give user error response
 *                  | success commit tx
 *              message queue
 *                  |
 *        book hotel in hotel service -> if fail -> rollback hotel db, -> send message to message queue -> commit flight cancel tx
 *                 | success commit tx
 *             message queue
 *                 |
 *        book  car in car service -> if fail -> rollback car db -> send message to message queue -> commit hotel cancel tx
 *                 | success commit tx
 *              message queue
 *                |
 *           notification / email
 *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *    BASE
 *      basic availability
 *      soft stage
 *      eventually consistency
 *   *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *   dead letter queue -> support team raise a ticket -> developers
 *
 *   *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *   tomorrow morning
 *       1. Testing , unit test, junit , mockito
 *       2. branch strategy + environment
 *       3. CI/CD pipeline
 *       4. daily work + agile scrum
 *       5. code review
 *       6. production support
 */
