package main.mysql;

import java.sql.*;
import java.util.ArrayList;

import main.frigon.*;

public class User {
    public ArrayList<Student> getStudent(String name) {
        PreparedStatement preState = null;
        ResultSet rs = null;
        ArrayList<Student> students = new ArrayList<Student>();
        try {
            MyConnection my = new MyConnection();
            preState = my.conn.prepareStatement("SELECT * FROM user where name like ?");
            preState.setString(1, "%"+name+"%");
            rs = preState.executeQuery();
            while(rs.next()){
                Student student = new Student();
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}