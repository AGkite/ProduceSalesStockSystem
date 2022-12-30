package dao.impl;

import beans.Logs;
import dao.LogDao;
import dbutils.DBHelper;
import utils.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogDaoimpl implements LogDao {
    @Override
    public  void updateLog(String type,String name,String state,String id,String count) {
        Connection conn = DBHelper.getConn();
        String sql = " insert into logs SET type='"+type+"',ShopName='" +name+ "',ShopId='"+id+"',State='"+state+ "',count='"+count+"',times='"+ DateUtil.getDateTimeNow()+"';";

        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(sql);
            stat.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBHelper.closeAll(conn, stat, rs);
        }

    }
    //查询出日志
    public List<Logs> queryLog() {
        Connection conn = DBHelper.getConn();
        String sql = "select * from Logs;";
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Logs> logslist = new ArrayList<Logs>();
        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()) {
                Logs log = new Logs();
                log.setShopName(rs.getString("ShopName"));
                log.setShopId(rs.getString("ShopId"));
                log.setState(rs.getString("State"));
                log.setCount(rs.getString("count"));
                log.setTimes(rs.getString("times"));
                log.setType(rs.getString("type"));
                logslist.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeAll(conn,stat,rs);
        }
        if (logslist.isEmpty())
            return null;
        else
            return logslist;
    }

}
