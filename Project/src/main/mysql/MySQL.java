package main.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQL{
    private static final String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String user = "root";
    private static final String pass = "zxy199595";

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public MySQL(){
        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("connected");
        } catch (Exception e) {
            System.out.println("fail to connect");
            e.printStackTrace();
        }
    }

    public String getData(String sql,String target){
        String result = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                result = rs.getString(target);
            }
            this.close();
        } catch (Exception e) {
            System.out.println("fail to get data");
            e.printStackTrace();
        }
        return result;
    }

    public boolean log(String username,String password){
        String sql = "select account,password from user where account = ? and password = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
           e.printStackTrace();
           return false;
        }finally{
            close();
        }
    }

    public String checkName(String account){
        String sql = "select name from user where account = ?";
        String name = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, account);
            rs = ps.executeQuery();
            while(rs.next()){
                name = rs.getString("name").strip();
            }
            System.out.println(name);
            return name;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            close();
        }
    }

    public void change(String account,String password){
        String sql = "update user set password = ? where account = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, account);
            ps.executeUpdate();
            ps.close();
            conn.close();
            System.out.println("closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId(String account){
        String sql = "select id from user where account = ?";
        int id = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, account);
            rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
            System.out.println(id);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }finally{
            close();
        }
    }

    public String getNotice(int id){
        String sql1 = "select admin from notice where id = ?";
        String sql2 = "select notice from notice where id = ?";
        String data = null;
        try {
            String data1 = null;
            String data2 = null;
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                data1 = rs.getString("admin").strip();
            }
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                data2 = rs.getString("notice").strip();
            }
            data = data1 + "&" + data2;
            System.out.println(data);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            close();
        }
    }

    public String getData(int id){
        String sql1 = "select name from user where id = ?";
        String sql2 = "select account from user where id = ?";
        String data = null;
        try {
            String data1 = null;
            String data2 = null;
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                data1 = rs.getString("name").strip();
            }
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                data2 = rs.getString("account").strip();
            }
            data = data1 + "&" + data2;
            System.out.println(data);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            close();
        }
    }

    public int getRow(String sql){
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery(sql);
            rs.last();
            int row = rs.getRow();
            System.out.println(row);
            return row;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }finally{
            try {
                this.conn.close();
                this.rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void insert(String name,String notice){
        String sql = "insert into notice (admin,notice) values (?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, notice);
            ps.executeUpdate();
            ps.close();
            conn.close();
            System.out.println("closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void close(){
        try {
            this.conn.close();
            this.rs.close();
            this.ps.close();
            System.out.println("closed");
        } catch (Exception e) {
            System.out.println("fail to close");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MySQL().getRow("select * from notice");
    }
}