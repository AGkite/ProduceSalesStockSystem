package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import beans.Goods;
import dao.GoodsDao;
import dbutils.DBHelper;
import utils.DateUtil;


public class GoodsDaoimpl implements GoodsDao{

	@Override
	public Goods findgoodsbyId(int no) {
		// TODO Auto-generated method stub
		if (no == 0) {
			return null;
		}
		Connection conn = DBHelper.getConn();
		String sql = "select*from goodsinfo WHERE no =" + no + ";";
		PreparedStatement stat = null;
		ResultSet rs = null;
		Goods goods = new Goods();

		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			while (rs.next()) {
				goods.setNo(rs.getInt("id"));
				goods.setGoodsName(rs.getString("goodsname"));
				goods.setCount(rs.getInt("count"));
				goods.setPrice(rs.getDouble("price"));
				goods.setGoodsName(rs.getString("type"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHelper.closeAll(conn, stat, rs);
		}
			return goods;
	}
	
	
	public Goods findgoodsbyId2(int no) {
		// TODO Auto-generated method stub
		if (no == 0) {
			return null;
		}
		Connection conn = DBHelper.getConn();
		String sql = "select*from soldout WHERE no =" + no + ";";
		PreparedStatement stat = null;
		ResultSet rs = null;
		Goods goods = new Goods();

		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			while (rs.next()) {
				goods.setNo(rs.getInt("id"));
				goods.setGoodsName(rs.getString("goodsname"));
				goods.setCount(rs.getInt("count"));
				goods.setPrice(rs.getDouble("price"));
				goods.setGoodsName(rs.getString("type"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHelper.closeAll(conn, stat, rs);
		}
			return goods;
	}
	
	@Override
	public Goods findgoodsbyname(String name) {
		// TODO Auto-generated method stub
		if (name == null) {
			return null;
		}
		Connection conn = DBHelper.getConn();
		String sql = "select*from goodsinfo WHERE name='"+ name + "';";
		//select*from goodsinfo WHERE name= 'www' ;
		PreparedStatement stat = null;
		ResultSet rs = null;
		Goods goods = new Goods();

		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			while (rs.next()) {
				goods.setGoodsName(rs.getString("goodsname"));
				goods.setCount(rs.getInt("count"));
				goods.setPrice(rs.getDouble("price"));
				goods.setGoodsName(rs.getString("type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHelper.closeAll(conn, stat, rs);
		}
		
			return goods;
	}

	
	@Override
	public void createNew() {
		// TODO Auto-generated method stub
		Connection conn = DBHelper.getConn();
		String sql = "INSERT goodsinfo(id,no,goodsname,count,price,type)VALUE(null,null,null,null,null,null);";
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(sql);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeAll(conn, stat, rs);

		}

	}

	@Override
	public void addgoods(String goodsname, int id) {
		// TODO Auto-generated method stub
		Connection conn = DBHelper.getConn();
		String sql = " UPDATE goodsinfo SET goodsname='" + goodsname + "'WHERE id='" + id + "';";
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

	@Override
	public void updatagoods(Goods goods) {
		// TODO Auto-generated method stub
		Connection conn = DBHelper.getConn();
		String sql = " UPDATE goodsinfo SET count='"+ goods.getCount()+"'WHERE id='" + goods.getNo() + "';";	
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

	@Override
	public int getid() {
		int serialNum = 0;
		Connection conn = DBHelper.getConn();
		String sql = "select*from goodsinfo Where no is null; ";
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			while (rs.next()) {
				serialNum = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHelper.closeAll(conn, stat, rs);
		}
		return serialNum;
	}
	@Override
	public void Drop(int no) {
		// TODO Auto-generated method stub
		Connection conn = DBHelper.getConn();
		//DELETE FROM <????> [WHERE ???] [ORDER BY ???] [LIMIT ???]
		String sql = "INSERT INTO soldout  (id,no,goodsname,count,price,type)  SELECT id,no,goodsname,count,price,type FROM goodsinfo"
				+ " where no='"+no +"';";
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
	public void Dropsoldout(int no) {
				Connection conn = DBHelper.getConn();
				//DELETE FROM <????> [WHERE ???] [ORDER BY ???] [LIMIT ???]
				String sql ="DELETE FROM goodsinfo WHERE no='"+no+"';";
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
	public Set<String> gettype() {
		Set<String> s=new HashSet<>();
		Connection conn = DBHelper.getConn();
		String sql = "select type FROM goodsinfo;";
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			while(rs.next())
			{
				String where=rs.getString("type");
				s.add(where);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHelper.closeAll(conn, stat, rs);
		}
		return s;
	}


	@Override
	public void addtalk(String talk,String no) {
		// TODO Auto-generated method stub
		Connection conn = DBHelper.getConn();
		String sql = "INSERT talk(talk,times,no)VALUE('"+talk+"','"+DateUtil.getDateTimeNow()+"','"+no+"');";
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(sql);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeAll(conn, stat, rs);

		}
		
	}
	public ArrayList<String> gettalk() {
		 ArrayList<String> ary=new ArrayList<>();
		Connection conn = DBHelper.getConn();
		String sql = "select*FROM talk;";
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			while(rs.next())
			{
				String s1=rs.getString("talk");
				String s2=rs.getString("times");
				String s4=rs.getString("no");
				String s3=s4+":"+s1+"            "+s2;
				ary.add(s3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHelper.closeAll(conn, stat, rs);
		}
		return ary;
	}
	
}
