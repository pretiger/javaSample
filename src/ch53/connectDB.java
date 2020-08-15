package ch53;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connectDB {

 public static void main(String[] args) throws ClassNotFoundException, SQLException {

  String connectionUrl = "jdbc:sqlserver://211.208.171.205:1433;" + "databaseName=school;";
  //(db서버가 따로 존재한다면 로컬호스트:포트번호 대신 서버아이피:포트번호 를 입력하면된다.
       // Declare the JDBC objects.
       Connection con = null;
       Statement stmt = null;
       ResultSet rs = null;

       try {
          // Establish the connection.

          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

          System.out.println("Driver okay");

          con = DriverManager.getConnection(connectionUrl,"sa","12345");

          System.out.println("Connection Made");

       }

       // Handle any errors that may have occurred.

       catch (Exception e) {

          e.printStackTrace();

       }

 

 }

}
