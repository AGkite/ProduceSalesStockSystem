package dbutils;

import java.sql.*;

public class DBHelper {
    public static Connection conn = null;
    public static String driver = "com.mysql.cj.jdbc.Driver";
    //public static String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=Asia/Shanghai&useSSL=false";
    public static String url = "jdbc:mysql://localhost:3306/shop?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
    public static String user = "root";
    public static String password = "123456";

    //连接数据库
    public static Connection getConn(){
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }

    //用于关闭数据库的连接
    public static void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs){
        if(conn!=null){
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(pstmt!=null){
            try{
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(rs!=null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

}
