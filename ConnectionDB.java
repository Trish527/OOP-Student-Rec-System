/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project;
import java.sql.*;

/**
 *
 * @author Ramyll
 */
public class ConnectionDB {
    public static Connection Connect() {
      Connection conn = null;
      try {
          Class.forName("oracle.jdbc.driver.OracleDriver");
          conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","plm","plmpass");
          System.out.println("Success");
      } catch (Exception e) {
          System.out.println(e);
      }
      return conn;
    }  
}
