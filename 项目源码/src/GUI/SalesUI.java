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

import javax.swing.JButton;
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
import service.GoodsService;

public class SalesUI extends JPanel {
	public DefaultTableModel defaultModel;
	public JTable jtable;
	public JLabel name, num, id;
	public JTextField namefield, numfield, idfield;
	public JButton yesButton, reButton;
	Connection conn = null;
	PreparedStatement stat = null;
	ResultSet rs = null;

	public SalesUI() {
		setLayout(new BorderLayout());
		// 创建表格
		Object[] names = { "商品编号", "商品名称", "商品数量", "商品单价", "商品类型" };
		Object[][] data = new String[1000][names.length];
		defaultModel = new DefaultTableModel(data, names);// 创建表格
		jtable = new JTable(defaultModel);

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
			stat = conn.prepareStatement("SELECT * FROM goodsinfo where count>3");
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

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 组装销售系统界面
		name = new JLabel("名称");
		id = new JLabel("编号");
		num = new JLabel("数量");

		namefield = new JTextField(10);
		idfield = new JTextField(10);
		numfield = new JTextField(10);

		yesButton = new JButton("确认销售");
		yesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = namefield.getText();
				String s1 = idfield.getText();
				String s2 = numfield.getText();
				
				if (s1.equals("") | s2.equals("")) {
					JOptionPane.showMessageDialog(null, "请正确输入");
				} else {
					int getnum = Integer.parseInt(s2);
					int getid = Integer.parseInt(s1);
					Goods g = new GoodsDaoimpl().findgoodsbyId(getid);
					if (g.getCount() < getnum) {
						JOptionPane.showMessageDialog(null, "库存不足");
					} else {
						new GoodsService().sale(getid,getnum);
						JOptionPane.showMessageDialog(null, "订购成功");
					}
				}

				try {
					int i = 0;
					conn = DBHelper.getConn();
					stat = conn.prepareStatement("SELECT * FROM goodsinfo where count>3");
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

			}
		});

		reButton = new JButton("刷新");
		reButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < defaultModel.getRowCount(); i++) {
					for (int j = 0; j < defaultModel.getColumnCount(); j++) {
						defaultModel.setValueAt("", i, j);
					}
				}
				try {
					int i = 0;
					conn = DBHelper.getConn();
					stat = conn.prepareStatement("SELECT * FROM goodsinfo where count>3");
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

			}
		});
		JButton dropButton = new JButton("下架");
		dropButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s1 = idfield.getText();
				if (s1.equals("")) {
					JOptionPane.showMessageDialog(null, "输入为空");
				} else {
					int getid = Integer.parseInt(s1);
					Goods g = new GoodsDaoimpl().findgoodsbyId(getid);
					if (g.getGoodsName() == null) {
						JOptionPane.showMessageDialog(null, "无此商品");
					} else {
						new GoodsDaoimpl().Drop(getid);
						new GoodsDaoimpl().Dropsoldout(getid);
						JOptionPane.showMessageDialog(null, "下架成功");
					}
				}

				try {
					int i = 0;
					conn = DBHelper.getConn();
					stat = conn.prepareStatement("SELECT * FROM goodsinfo where count>3");
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
					namefield.setText(" ");
					idfield.setText(" ");
					numfield.setText(" ");
				}

			}
		});
		idfield.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (!(ch >= '0' && ch <= '9')) {
					e.consume();
				}
			}
		});
		numfield.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (!(ch >= '0' && ch <= '9')) {
					e.consume();
				}
			}
		});

		JPanel p1 = new JPanel();
		//p1.add(name);
		//p1.add(namefield);
		p1.add(id);
		p1.add(idfield);
		p1.add(num);
		p1.add(numfield);

		JPanel p2 = new JPanel();
		p2.add(yesButton);
		p2.add(reButton);
		p2.add(dropButton);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.add(p1);
		panel.add(p2);

		add(s, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);

		setVisible(true);
	}

}
