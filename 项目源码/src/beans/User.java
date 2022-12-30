package beans;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutils.DBHelper;

public class User {
    private int userId;
    private String userPassword;

    public User() {
        super();
    }

    public User(int userId, String userPassword) {
        super();
        this.userId = userId;
        this.userPassword = userPassword;

    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void User_Register(int userId, String userPassword) {
        PreparedStatement stmt1=null;
        ResultSet rs1=null;

        Connection conn1=null;
        this.userId = userId;
        this.userPassword = userPassword;
        try{
            conn1= DBHelper.getConn();
            String sql="insert into user(userid,userpassword)values"+"('"+userId+"',md5('"+userPassword+"'))";
            stmt1=conn1.prepareStatement(sql);
            stmt1.executeUpdate();
            //int count=stmt1.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeAll(conn1,stmt1,rs1);
        }
    }
    public void Buy_Register(int userId, String userPassword,String adress,String phone) {
        PreparedStatement stmt1=null;
        ResultSet rs1=null;

        Connection conn1=null;
        this.userId = userId;
        this.userPassword = userPassword;
        try{
            conn1= DBHelper.getConn();
            String sql="insert into user(userid,userpassword,buy,adress,phone)values"+"('"+userId+"',md5('"+userPassword+"'),'"+"yes"+"','"+adress+"','"+phone+"')";
            //insert into user(userid,userpassword,buy)values(1,1,'yes')
            stmt1=conn1.prepareStatement(sql);
            stmt1.executeUpdate();
            //int count=stmt1.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeAll(conn1,stmt1,rs1);
        }
    }
}
