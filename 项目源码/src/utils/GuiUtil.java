package utils;

import dbutils.DBHelper;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

public class GuiUtil {
    public static boolean k=false;
    //判断购买数量是否为整数
    public static void digit(String str){
        try{
            Integer.parseInt(str);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"输入的不是整数，请重新输入！");
            k=true;
        }

    }
    //判断输入的字段是否存在数据库
    public static boolean t = false;
    public static void haveName(String str){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Vector nameSet = new Vector();

        try {
            conn = DBHelper.getConn();
            stat = conn.prepareStatement("select goodsname from goodsinfo;");
            rs = stat.executeQuery();
            while (rs.next()){
                nameSet.add(rs.getString("goodsname"));
            }
            Iterator it = nameSet.iterator();
            while(it.hasNext()){
                if(str.equals(it.next())){
                    JOptionPane.showMessageDialog(null,"输入的商品名不存在，请重新输入。");
                    t = true;
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean isNumeric4(String str) {
        if (str == null) return false;
        for (char c : str.toCharArray ()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

}
