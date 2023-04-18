package com.example.java3.week6;

/**
 * Cloud ?
 *
 *
 * ---------------------------------------------
 *                                                  Route53
 *                                              /                       \
 *                            region1                                                       region2
 *                              |
 *                          API Gateway
 *                              |
 *                 Application LoadBalancer(private ip / public ip)
 *                  /               \
 *            /student              /teacher
 *      ASG[EC2, EC2, EC2]        ASG[EC2, EC2]    -----  S3  -----  S3 glacier
 *          target1                 target2
 *            |                     |
 *           RDS                   RDS  -  stand by
 *                                  |
 *                              replica 1 2 3
 *
 *
 *
 *   1. VPC
 *                   internet gateway
 *                         |
 *                 public subnet    -   private subnet  - NAT -
 *
 *   2. SNS, SQS
 *          SQS (queue)
 *              visibility timeout
 *              standard queue (no order)
 *              FIFO queue (order)
 *          SNS (topic - subscriber)
 *                  -   SQS
 *              SNS -   SQS
 *                  -   SQS
 *                  -   Text / email service
 *   3. Lambda
 *          S3  -  Lambda   -
 *   4. CloudWatch
 *   5. CloudTrail
 *   6. CloudFront (CDN)
 *
 *         1.  user      -    website
 *         2.  save data into edge location
 *   7. ECS, ECR(docker image repository)
 *          APP - docker image  - run docker container
 *          APP - jar   - run server
 *          APP - war   - upload war to server
 *
 *          1. package docker image
 *          2. upload image to ECR
 *          3. in ECS, use task definition
 *          4. generate / run docker containers in EC2 / Fargate
 *
 *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *    option1. get one of Developer Associate / Solution Architect Associate certificate
 *    option2. watch youtube / udemy aws videos
 *
 *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *
 *
 */