package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import beans.Goods;
import dao.impl.GoodsDaoimpl;
import dbutils.DBHelper;

public class StockUI extends JPanel {

	JTable jtable;
	public DefaultTableModel defaultModel;
	JPanel  jPanel1, jPanel2;
	JLabel shopname;
	JTextField shopname_f;
	JButton soldOut, sift;
	JComboBox<String> choose = new JComboBox<>();

	Connection conn = null;
	PreparedStatement stat = null;
	ResultSet rs = null;

	public StockUI()  {
		setLayout(new BorderLayout());

		// 创建表格
		Object[] names = { "商品编号", "商品名称", "商品数量", "商品单价", "商品类型" };
		Object[][] data = new String[100][names.length];
		defaultModel = new DefaultTableModel(data, names);// 创建表格
		jtable = new JTable(defaultModel);
		// jtable.setPreferredScrollableViewportSize(new Dimension(400, 350));
		JScrollPane s = new JScrollPane(jtable);
		// 将jtable中的数据全部居中
		DefaultTableCellRenderer dTCReader = new DefaultTableCellRenderer();
		dTCReader.setHorizontalAlignment(SwingConstants.CENTER);
		for (Object s1 : names) {
			jtable.getColumn(s1).setCellRenderer(dTCReader);
		}

		try {
			int i = 0;
			conn = DBHelper.getConn();
			stat = conn.prepareStatement("SELECT * FROM goodsinfo;");
			rs = stat.executeQuery();
			while (rs.next()) {
				String id = rs.getString("no");
				String goodsname = rs.getString("goodsname");
				String count = rs.getString("count");
				String price = rs.getString("price");
				String type = rs.getString("type");
				jtable.setValueAt(id, i, 0);
				jtable.setValueAt(goodsname, i, 1);
				jtable.setValueAt(count, i, 2);
				jtable.setValueAt(price, i, 3);
				jtable.setValueAt(type, i, 4);
				i++;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Set<String> ss=new GoodsDaoimpl().gettype();
		for(String s1:ss) {
			choose.addItem(s1);
			
		} 
		choose.addItem("全部");
		choose.addItem("下架商品");

		sift = new JButton("筛选");
		sift.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < defaultModel.getRowCount(); i++) {
					for (int j = 0; j < defaultModel.getColumnCount(); j++) {
						defaultModel.setValueAt("", i, j);
					}
				}
				try {
					int i = 0;
					String strchooose = (String) choose.getSelectedItem();
					conn = DBHelper.getConn();
					if (strchooose.equals("全部")) {
						stat = conn.prepareStatement("SELECT * FROM goodsinfo;");
						rs = stat.executeQuery();
						while (rs.next()) {
							String id = rs.getString("no");
							String goodsname = rs.getString("goodsname");
							String count = rs.getString("count");
							String price = rs.getString("price");
							String type = rs.getString("type");
							jtable.setValueAt(id, i, 0);
							jtable.setValueAt(goodsname, i, 1);
							jtable.setValueAt(count, i, 2);
							jtable.setValueAt(price, i, 3);
							jtable.setValueAt(type, i, 4);
							i++;
						}
					} else if (strchooose.equals("下架商品")) {
						stat = conn.prepareStatement("SELECT * FROM soldout;");
						rs = stat.executeQuery();
						while (rs.next()) {
							String id = rs.getString("no");
							String goodsname = rs.getString("goodsname");
							String count = rs.getString("count");
							String price = rs.getString("price");
							String type = rs.getString("type");
							jtable.setValueAt(id, i, 0);
							jtable.setValueAt(goodsname, i, 1);
							jtable.setValueAt(count, i, 2);
							jtable.setValueAt(price, i, 3);
							jtable.setValueAt(type, i, 4);
							i++;
						}
					} else {
						stat = conn.prepareStatement("SELECT * FROM goodsinfo where type='" + choose.getSelectedItem() + "';");
						rs = stat.executeQuery();
						while (rs.next()) {
							String id = rs.getString("no");
							String goodsname = rs.getString("goodsname");
							String count = rs.getString("count");
							String price = rs.getString("price");
							String type = rs.getString("type");
							jtable.setValueAt(id, i, 0);
							jtable.setValueAt(goodsname, i, 1);
							jtable.setValueAt(count, i, 2);
							jtable.setValueAt(price, i, 3);
							jtable.setValueAt(type, i, 4);
							i++;
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		shopname = new JLabel("上架商品编码");
		shopname_f = new JTextField(10);
		shopname_f.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (!(ch >= '0' && ch <= '9')) {
					e.consume();
				}
			}
		});
		soldOut = new JButton("上架");
		soldOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String strshop = shopname_f.getText();
				conn = DBHelper.getConn();
				if (strshop.equals(""))
				{
						JOptionPane.showMessageDialog(null,"输入为空");
				}else {
				int no = Integer.parseInt(strshop);
				try {					
				Goods g=new GoodsDaoimpl().findgoodsbyId2(no);
				if(g.getGoodsName()==null) {
					JOptionPane.showMessageDialog(null,"此物品不是下架商品");
					}
				else {
					stat = conn.prepareStatement("insert into goodsinfo (no,goodsname,count,price,type) select no,goodsname,count,price,type from soldout where no='"+strshop+"'");
					stat.execute();
					stat=conn.prepareStatement("delete from soldout where no='"+strshop+"'");
					stat.execute();					
					JOptionPane.showMessageDialog(null,"上架成功!");
					
				}				
				} 					
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}}
		});

		jPanel1 = new JPanel();
		jPanel1.add(shopname);
		jPanel1.add(shopname_f);

		jPanel2 = new JPanel();
		jPanel2.add(soldOut);

		JPanel sum = new JPanel();
		sum.setLayout(new GridLayout(2, 2));

		JPanel jPanel3 = new JPanel();
		JPanel jPanel4 = new JPanel();
		jPanel3.add(choose);
		jPanel4.add(sift);
		sum.add(jPanel3);
		sum.add(jPanel1);
		sum.add(jPanel4);
		sum.add(jPanel2);

		add(s, BorderLayout.CENTER);
		add(sum, BorderLayout.SOUTH);
		setVisible(true);
	}
}
