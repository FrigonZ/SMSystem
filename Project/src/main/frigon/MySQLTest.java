package main.frigon;

import java.sql.*;

public class MySQLTest {

    public static void main(String[] args)throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String myConnectionString = "jdbc:mysql://localhost:3306/test?"+ "useUnicode=yes&useSSL=false&serverTimezone=GMT";
        Connection conn = DriverManager.getConnection(myConnectionString, "root", "zxy199595");
        System.out.println(conn);
    }
}