package main.mysql;

import java.sql.*;

public class MyConnection{

    String url = "jdbc:mysql://localhost:3306/test?"+ "useUnicode=yes&useSSL=false&serverTimezone=GMT";
    String myUser = "root";
    String myPassword = "zxy199595";
    Connection conn;

    public MyConnection() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        conn = DriverManager.getConnection(url, myUser, myPassword);
    }
}