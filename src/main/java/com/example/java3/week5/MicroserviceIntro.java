package com.example.java3.week5;


/**
 *                          |
 *                    loadbalancer
 *                  /      |    \       \       \
 *      server-> node1, node2, node3  node4     node5
 *
 *  Monolithic
 *      1. scalability (vertical scaling , horizontal scaling)
 *      2. coupled
 *      3. deployment
 *
 *  Why Microservice
 *                  service1(node1, node2, node3)
 *                /       |     \
 *              s2       s3     s4
 *      1. loose coupling
 *      2. deployment
 *      3. scalability
 *      4. impl services with diff coding language
 *      5. message queue can take more requests..
 *      ...
 *  Challenges with Microservices (CAP / BASE)
 *      1. communication
 *      2. test entire system
 *      3. documents
 *      4. design + complexity of business logic + service boundary
 *      5. fail tolerance
 *      6. database cluster / sharding / global transaction + cache clusters
 *      7. message queue
 *      8. take of ips / locations
 *      9. sync time for diff services
 *      10. api gateway service -> global unique id / rate limiter
 *      11. log -> centralize log -> analyze log -> build index -> provide searching function
 *      12. deployment (kubernetes / ECS / container management tools)
 *      13. CI/CD
 *      14. centralize security service
 *      15. centralize properties
 *      ....
 *
 *  How to design Microservices
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  this afternoon office hour 2pm cdt
 *
 *
 */