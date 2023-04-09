package com.example.java3.week6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  agile
 *  scrum
 *      product backlog (all todo features + priority)
 *      sprint(2 - 3 weeks)
 *      sprint planning meeting
 *          get stories / tickets from product backlog
 *          estimate time / point of stories / tickets
 *                  1 point = 2h
 *                  fib number : 1 2 3 5 8
 *
 *      daily stand up meeting
 *      retrospective meeting / review meeting
 *
 *      *      *      *      *      *      *      *      *      *      *      *      *
 *      get story
 *      1. analyze stories
 *      2. clarification -> BA/QA/Manager corner cases
 *      3. OOD design / feature design
 *          check out feature branch
 *      4. Abstract class / interface / Class Impl + //TODO
 *      5. write Test cases for each methods / diff methods
 *      6. finish //TODO
 *      7. run test cases
 *          commit push, pull request code review -> merge to development branch
 *
 *      CI/CD
 *      8. triggered by git hook / branch changes
 *      9. build -> test -> report -> package -> deploy
 *                           |
 *                        sonar qube
 *
 *      *      *      *      *      *      *      *      *      *      *      *      *
 *
 *
 *   master branch      ---------------                                                     prod env
 *   hotfix branch
 *                                     / v1.0.0
 *
 *   release branch     ---------------------------------------------------------------     uat env
 *                                         / v1.0.1                    / v1.0.2
 *   development branch ---------------------------------------------------------------     dev env
 *                          \               / pull request code review + merge
 *   feature branch          --------------                                                 local env
 *
 *  product support / bug fix ticket
 *      checkout hotfix branch
 *      1. read bug report
 *      2. check log - Splunk / Cloudwatch
 *      3. reproduce issue
 *      4. application / code level issues
 *         server issues (not registered in discovery service / not registered in LB / network / fire wall..)
 *         app configuration / system configurations
 *         database (dirty data)
 *      5. rewrite some test cases + fix bugs
 *      6. pull request code review + merge
 *
 *
 *********
 * what is thread safe
 *     1.
 * how to keep thread safe
 *
 * what is volatile
 *
 * what is @transactional
 *
 * what is @Async
 *
 * list<Integer> -> sum -> value
 *      use stream api vs for loop
 * what is thread pool
 *      fixedThreadPool, cachedThreadPool, scheduled thread pool
 *      fork join pool
 *
 *      ThreadPoolExecutor
 *          1. min
 *          2. max
 *          3. time unit
 *          4. alive time
 *          5. blocking queue
 *          6. thread factory
 * coding: write code with synchronized keyword
 *      public synchronized void get() {
 *
 *      }
 *
 *      public void get() {
 *          synchronized(this) {
 *
 *          }
 *      }
 *
 *      public void set() {
 *          synchronized(this) {
 *
 *          }
 *      }
 *
 *  homework:
 * coding: impl customized thread pool(create customized blocking queue)
 *       1. user can user your thread pool
 *       2. submit runnable into thread pool => execute runnable
 */
class Test {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();//[1, 2, 3, 4, 5, 6]
        l.parallelStream().map(x -> x * 2).collect(Collectors.toList());
        /**                         T1 [1, 2]       -> List[2, 4]
         *  [1, 2, 3, 4, 5, 6]      T2 [3, 4, 5]    -> List[6, 8, 10]       -> combiner -> [2, 4, 6, 8, 10, 12]
         *                          T3 [6]          -> List[12]
         */
    }
}

