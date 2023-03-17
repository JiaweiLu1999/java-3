package com.example.java3.week3;

import java.sql.*;


/**
 * ASCii : 0 ~ 127 standard characters  ,  128 ~
 *
 * 215 -> encode -> 0 ~ 127 characters -> decode
 *
 *
 *
 *
 *
 * public ip :
 * private ip :
 *                                  route table [public ip1 + port2] -> [private ip2 + port2]
 *                                  route table [public ip1 + port1] -> [private ip1 + port1]
 *                                    [public ip1, port1 + public ip2, port]
 * mac address <=> private ip <-> NAT(3 public ips) <-> build connection[public ip1, port,  public ip2, port] <-> public ip2 server
 *                 100 people
 *
 *
 * 1. connection : [source ip, source port + dest ip, dest port]
 *      TCP
 *
 *      [ip header][TCP header][http header][data]
 *
 *
 *      [0][010101010101101][xxx][]
 *
 *    A Application Layer : HTTP
 *    P ? Layer : ssl
 *    S Session Layer : Socket
 *    T Transport Layer : TCP/UDP
 *    N Network Layer : IP
 *    D Data Link Layer : mac address
 *    P Physical Layer : cable
 *
 *
 *    http://www.amazon.com/.. => domain name => get ip from dns
 *    http://ip/..
 *
 *    ipv4 range 0.0.0.0 ~ 255.255.255.255
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *    login page
 *    username: [x1x2]
 *    password: [x1x1 or true]
 *
 *    SQL injection
 *
 *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *    improve JDBC
 *    1. reuse connections -> connection pool -> blocking queue
 *    2. wrap connection info -> data source
 *    3. centralized query / hql / jpql
 *          write hql -> execute program -> translate hql(database_dialect) -> native query
 *    4. criteria query / dynamic query
 *    5. mapper -> map result -> instance
 *    6. @Transactional + rollback
 *
 *
 *    homework:
 *      1. install relational database in your laptop
 *      2. create teacher m - m student relations in database
 *      3. read sequence concept, create sequence or create auto increment primary key
 *      4. read ORM concept / hibernate (demo)
 *      5. check lazy loading and eager loading from hibernate
 *      6. read hibernate demo code
 *      7. review previous topics
 */

public class JdbcExample {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:8080/EMP";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";


    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //STEP 2: Register JDBC driver -> DriverManager
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(false);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            String username = "";
            String password = "'xxx OR TRUE; DROP table'";
            String sql1 = "SELECT .... first, last, age FROM Employees WHERE username = "
                    + username + " AND password = " + "\'x1x2 or true\'";
            stmt = conn.prepareStatement(sql1);
            ResultSet rs = stmt.executeQuery();

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            conn.commit();
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }//end main
}