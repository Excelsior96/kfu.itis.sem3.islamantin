package swingapp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class DBConnection {  
  private static Connection conn;
  
  public static Connection getConnection() throws DBException{
    if(conn == null){
      try{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","211212");
      }
      catch(ClassNotFoundException ex){
        throw new DBException("Can't find DB driver.");
      } catch (SQLException ex) {
        throw new DBException("Can't connect to DB (" + ex.getMessage() + ").");
      }      
    }
    return conn;
  }
}
