Day 17 documentation
@Jiawei Lu
-------------------------------------------------------------------------------
Design student m - m teacher endpoints

Students:
1. get all students
     endpoint : /student
     method : GET
     status code : 200 / 4xx / 5xx
     response body:
        {
            "data": [{}, {}, {}, ...]
        }

2. get student by id
     endpoint : /student/{id}
     method : GET
     status code : 200 / 4xx / 5xx
     response body:
        {
            "name": xxx,
        }

3. get teachers of one student by studentId
     endpoint : /student/{id}/teacher
     method : GET
     status code : 200 / 4xx / 5xx
     response body:
        {
            "data": [{}, {}, {}, ...]
        }

4. create student
     endpoint : /student
     method : POST
     status code : 201 / 4xx / 5xx
     request body:
        {
            "name": xxx,
        }
     response body:
        {
            "id": xxx
        }

5. update student
     endpoint : /student/{id}
     method : PUT
     status code : 204 / 4xx / 5xx
     request body:
        {
        "id": xx,
        "name": xxx,
        }

Teachers:
1. get all Teachers
     endpoint : /teacher
     method : GET
     status code : 200 / 4xx / 5xx
     response body:
        {
            "data": [{}, {}, {}, ...]
        }

2. get teacher by id
     endpoint : /teacher/{id}
     method : GET
     status code : 200 / 4xx / 5xx
     response body:
        {
            "name": xxx,
        }

3. get students of one teacher by studentId
     endpoint : /teacher/{id}/student
     method : GET
     status code : 200 / 4xx / 5xx
     response body:
        {
            "data": [{}, {}, {}, ...]
        }

4. create teacher
     endpoint : /teacher
     method : POST
     status code : 201 / 4xx / 5xx
     request body:
        {
            "name": xxx,
        }
     response body:
        {
            "id": xxx
        }

5. update teacher
     endpoint : /teacher/{id}
     method : PUT
     status code : 204 / 4xx / 5xx
     request body:
        {
        "id": xx,
        "name": xxx,
        }