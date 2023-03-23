package com.example.java3.week4;

/**
 *
 *    request -> Tomcat
 *                 Thread -> get request info -> process request
 *                 Thread -> /employee : EmployeeServlet class(doGet, doPost)
 *
 *    request -> Tomcat -> SpringMVC[dispatcherServlet: /* -> handlerMapping -> Controller class] -> service -> repository
 *                                          |
 *                                        view resolver
 *                                         |
 *                                       jsp
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   Http(tcp)
 *       tcp connection
 *      -> request tcp->
 *      <- response tcp<-
 *       tcp disconnection
 *
 *   endpoint : http://www.domain.com/v1/employee
 *   method : get / post / patch / put / delete / head..
 *          get(no request body) : retrieve data
 *          post : create data
 *          put : update data
 *          patch : partial updating
 *          delete : delete data
 *          head : get without response body
 *   status code :
 *          2xx (success) : 200(ok), 201(created), 204(OK no Content)
 *          3xx (redirection) : 301, 302
 *          4xx (client side error) : 400(bad request), 401, 403, 404(resource not found)
 *          5xx (server side error) : 500
 *   header :
 *          content length
 *          accept : json / xml
 *          content type : json / xml
 *          authorization : bearer token
 *          ..
 *   request body / response body
 *          {
 *              "key1": "value",
 *              "key2": [{}, {}, {}],
 *              "key3": {
 *                  "key4": "value"
 *              }
 *          }
 *          [{}, {}]
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *     \
 *   cookie(user cache in browser side) vs session(user cache in server side)
 *
 *   user -> request -> server(session(session_id, value))
 *   1. visit server
 *   2. server generate session_id
 *   user <- response(include session_id) <- server
 *   3. save it in cookie
 *
 *
 *   request 1 -> /employee get => retrieve all employee
 *   request 2 -> /calculation get => calculate data from session
 *
 *
 *
 *                  user
 *                    |
 *                loadbalancer (geo location / round robin / weight / network speed / ..)
 *                /   |     \
 *              s1    s2    s3
 *                \   |    /
 *                  cache
 *   session is saved in diff servers, request couldn't get session from previous visited server
 *   1. sticky session
 *   2. global shared session
 *   *   *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *  Restful API
 *
 *     1. http
 *          endpoint
 *          method
 *          status code
 *          header
 *          request body
 *          response body
 *     2. stateless
 *
 *
 *     design endpoints for employee data / table
 *     emp table (id(pk), name)
 *     1. create emp
 *          endpoint : /employee
 *          method : post
 *          status code : 201 / 4xx / 5xx
 *          request body :
 *              {
 *                  "name" : "Tom"
 *              }
 *          response body :
 *              {
 *                  "id"/"relocation_id" : xx
 *              }
 *     2. update emp
 *          endpoint : /employee/{id}
 *          method : put / patch
 *          status code : 204 / 4xx / 5xx
 *          request body :
 *              {
 *                  "name" : "Jerry"
 *              }
 *     3. get all emp
 *          endpoint : /employee?salary=xx
 *          method : get
 *          status code : 200 / 4xx / 5xx
 *          response body :
 *              {
 *                  data : [{}, {}, {}]
 *              }
 *     4. get emp by id
 *          employee : /employee/{id}
 *          method : get
 *     5. delete emp
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   request param vs path variable
 *   /employee/{id}  path variable
 *   /employee?filter=xx  request param
 *
 *   /employee/{e_id}/department/{d_id}/xx
 *   /employee/department?e_id=xx&d_id=xx
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   versioning
 *      /employee => v1, v2, v3
 *      1. /v1/employee
 *      2. request param
 *      3. header
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   idempotent
 *      post : is not idempotent
 *      put : is idempotent
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *      Tomorrow :
 *          Restful API implementation
 *
 *      @RestController
 *      @RequestMapping("/employees")
 *      class EmployeeController {
 *          @PostMapping("")
 *          public xx createEmp() {
 *
 *          }
 *      }
 *
 *
 *      homework:
 *          1. create spring boot application, add all dependencies
 *          2. write read_me
 *              design student m - m teacher endpoints
 *              get all students
 *              get all teachers
 *              get stu by id
 *              create stu
 *              update stu
 *              when user provide student id, retrieve all teachers related to this student
 *          3. check pagination concept
 *
 *      Tomorrow's homework
 *          1. create controller + service + repository + entity
 *          2. handle exception in controller + globally
 *          3. log info / error ...
 *          4. use response DTO / request DTO instead of entity
 */