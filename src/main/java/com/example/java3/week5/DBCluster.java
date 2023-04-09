package com.example.java3.week5;

/**
 *  Random IO
 *      B+ tree
 *  Sequential IO
 *      LSM tree (log structure merged index tree???)
 *
 *             merge to larger file3
 *              /               \
 *         file1                file2
 *      update id = 1       delete id = 1
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  master - slave
 *  leader - follower
 *  primary - secondary
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Single Leader System
 *  write -> leader / master / primary node
 *  read -> leader / replica
 *
 *  RDBMS
 *
 *      app
 *       |
 *      DB  -   replica1  /  replica2  /  replica3
 *      |
 *    stand by DB
 *
 *
 *      app
 *       |
 *      DB  -   replica1  /  replica2  /  replica3
 *
 *
 *    write -> db
 *    1. write to master, return OK to user
 *          sync data to replica + stand by in background
 *    2. write to master, sync data to all replicas + stand by
 *          if the master DB receives (1 ~ N) success response from replica / from stand by
 *          return OK to user
 *
 *    read -> db
 *    1. read from master node
 *    2. read from replica
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *   Multi-Leader System
 *
 *          Leader1     =   Leader2     =      Leader3
 *       /      \           /       \           /       \
 *     replica1 2         1         2           1       2
 *
 *     write -> leader1 / 2 / 3
 *     read ->
 *
 *     update table set name = 'x' where  -> leader1
 *     update table set name = 'y' where  -> leader2
 *
 *     clock vector
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *   LeaderLess System = All Leaders System
 *
 *                      N1(0) id=3
 *
 *          N6(60k)              N2 (10k)  id=1,3
 *
 *          N5(50k)              N3 (20k)  id=1,3
 *
 *                  N4(40k) id=1
 *
 *
 *        replica = 3
 *
 *  Cassandra (AP)
 *
 *                      N1(0) id=3
 *
 *          N6(60k)              N2 (10k)  id=1,3
 *
 *          N5(50k)              N3 (20k)  id=1,3
 *
 *                  N4(40k) id=1
 *
 *
 *   FETCH id = 1   -> N5 ->    N2
 *                              N3
 *                              N4
 *    Write Consistency = 1
 *    Read  Consistency = 2
 *      (check network speed,
 *       fetch data from one node,
 *       fetch hash value from another node,
 *       compare data
 *       if data not same => read repair on these two nodes
 *       return mode recent data from these two nodes)
 *    Replica Factor = 3
 *
 *    W + R > RF ?
 *
 *
 *
 *    cassandra node
 *
 *       write  ->   mem table -> dump into -> SSTable / Sorted String Table(immutable)
 *                  |
 *             commit log
 *
 *
 *                    SSTable4
 *                  /           \
 *              SSTable1        SSTable2         SSTable3
 *
 *      update = upsert = update if primary key exist / insert row
 *
 *      update ..where pk = xx (upsert)  = update if primary key exist / insert row
 *      update ..where pk = xx if exist (update)
 *
 *
 *      read  -> mem table
 *            -> blooming filter
 *            -> search sst -> index
 *            -> merge result
 *
 *
 *     ??? blooming filter
 *     id = 1
 *     SST1 hashFunc1()[][][][x][][][][][][][][]
 *          hashFunc2()[][][][][][][][][][x][][]
 *          hashFunc3()[][x][][][][][][][][][][]
 *
 *     SST [][][][][][][][][][][][]
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Sharding  -  index issues
 *      1. split data into even / odd db
 *          even id         odd id
 *            DB1            DB2
 *          /   \           /   \
 *        REPLICAS          ..
 *
 *        client => id % 2 = 0 / 1 => find db
 *     2. add one more leader into cluster,  db1(id = 0, 3, 6..), db2(id = 1, 4, 7..), db3(id = 2, 5, 8..)
 *            DB1            DB2            DB3
 *          /   \           /   \           /  \
 *        REPLICAS          ..              ..
 *
 *        client => id % 3 = 0 / 1 / 2 => find db
 *
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Redis Cluster
 *
 *  around 25k hash slots
 *                   DB1            DB2               DB3
 * hash slots     0 ~ 10k       10k + 1 ~ 20k       20k + 1 ~ 25k
 *                  /   \           /   \             /  \
 *              REPLICAS           ..                ..
 *                  |
 *               DB1        DB1.2
 *              0 ~ 5k     5k+1 ~ 10k
 *
 *        id + 25k hash slots => index
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Consistency
 *  Availability
 *  Partition Tolerance
 *  (NO CAP , NO CA)
 *   *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  MongoDB (CP)
 *
 *                      Mongos(Gateway)     -      Mongos(Config) : id: sharding
 *                  /           |           \
 *           sharding1      sharding2       sharding3
 *           Primary        ..              ..
 *           Secondary
 *           Secondary
 *           nodes
 *          0 ~ 10k         ..
 *
 *  sharding key / partition key
 *      1. user_id => faster range search
 *      2. hash(user_id) => evenly distributed data
 *  what if i want to select name = 'Tom'
 *      1. local index
 *      2. global 2nd index
 *              read data
 *              a. visit global 2nd index
 *              b. get sharding location
 *              c. send request to sharding
 *              d. use cluster / local index in sharding
 *
 *
 *              write (global transaction)
 *              transaction
 *              a. insert data into mongodb cluster
 *              b. insert sharding info + value into global 2nd index cluster
 *              end
 *
 *   *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *   next Monday + Tuesday
 *       1. code review
 *       2. Cassandra
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *       3. 2PC commit
 *       4. Message queue
 *       5. CDC + outbox pattern
 *       6. Saga Pattern
 *   Wednesday + Thursday
 *       1. Testing , unit test, junit , mockito
 *       2. branch strategy + environment
 *       3. CI/CD pipeline
 *       4. daily work + agile scrum
 *       5. code review
 *       6. production support
 *   Friday
 *       1. aws introduction
 *
 *   AWS developer associate / solution architect associate = GCP
 */