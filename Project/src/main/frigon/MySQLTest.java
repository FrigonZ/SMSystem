package main.frigon;

import java.sql.*;

public class MySQLTest {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "zxy199595";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            try {
                conn = DriverManager.getConnection(url, user, password);
                System.out.println(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}