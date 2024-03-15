package belajar.mvc.jdbc.tools;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

  private static final String jdbUrl = "jdbc:mysql://localhost:3307/sib_db_hr";
  private static final String username = "root";
  private static final String password = "root";

  public static Connection getConnection() {
    try {
      Driver mySqlDriver = new com.mysql.cj.jdbc.Driver();
      DriverManager.registerDriver(mySqlDriver); // melakukan registrasi driver mysql

      return DriverManager.getConnection(jdbUrl, username, password);
    } catch (SQLException exception) {
      System.err.println("ERROR : " + exception.getMessage());
      throw new RuntimeException(exception);
    }
  }
}
