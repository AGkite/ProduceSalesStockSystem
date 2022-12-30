package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import beans.Goods;
import dao.impl.GoodsDaoimpl;
import dbutils.DBHelper;
import service.GoodsService;

public class UserUI extends JFrame {

	public Font font;
	public JLabel title;
	public DefaultTableModel defaultModel;
	public JTable jtable;
	public JLabel num, id;
	public JTextField numfield, idfield;
	public JButton yesButton, reButton, sift,talk,say;
	JComboBox<String> choose = new JComboBox<>();
	Connection conn = null;
	PreparedStatement stat = null;
	ResultSet rs = null;

	public UserUI(String sss) {

		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		// TODO 自动生成的构造函数存根
		setTitle("大帝百货");
		setBounds(300, 200, 1050, 500);
		setLayout(new BorderLayout());
		font = new Font("华文行楷", Font.PLAIN, 36);
		title = new JLabel("              大帝百货欢迎您！！！");
		title.setFont(font);

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

		Set<String> ss = new GoodsDaoimpl().gettype();
		for (String s1 : ss) {
			choose.addItem(s1);

		}
		choose.addItem("全部");

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
					} else {
						stat = conn.prepareStatement("SELECT * FROM goodsinfo where type='" + choose.getSelectedItem() + "' and count>3;");
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

		id = new JLabel("编码");
		idfield = new JTextField(10);
		idfield.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (!(ch >= '0' && ch <= '9')) {
					e.consume();
				}
			}
		});
		num = new JLabel("数量");
		numfield = new JTextField(10);
		numfield.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (!(ch >= '0' && ch <= '9')) {
					e.consume();
				}
			}
		});
		yesButton = new JButton("确认购买");
		yesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
						new GoodsService().sale(getid, getnum);
						JOptionPane.showMessageDialog(null, "订购成功");
					}
				}

				try {
					int i = 0;
					conn = DBHelper.getConn();
					stat = conn.prepareStatement("SELECT * FROM goodsinfo where count>3;");
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
		talk = new JButton("留言");
		talk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf1=new JFrame();
				jf1.setTitle("留言给客服");
				JTextArea jt1=new JTextArea(10,20);
				JButton send=new JButton("发送");
				jf1.setLocationRelativeTo(null);
				jf1.add(jt1,BorderLayout.NORTH);
				jf1.add(send,BorderLayout.SOUTH);
				jt1.setLineWrap(true);        //激活自动换行功能 
				jf1.pack();
				jf1.setVisible(true);
				
				jt1.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
						String s=jt1.getText();
						if (s.length()>1000) {
							e.consume();
						}
					}
				});
				send.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String s=jt1.getText();
						if(s.equals("")) {
					JOptionPane.showMessageDialog(null, "请输入内容");
				}
				else {
					new GoodsDaoimpl().addtalk(s,sss);
					JOptionPane.showMessageDialog(null, "发送成功");
				}
						
					}});
				
			}
		});
		say = new JButton("客服");
		say.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                TcpClientUI clientUIUI=new TcpClientUI();
			}
		});
		JPanel panel = new JPanel();
		panel.add(choose);
		panel.add(sift);
		panel.add(id);
		panel.add(idfield);
		panel.add(num);
		panel.add(numfield);
		panel.add(yesButton);
		panel.add(talk);
		panel.add(say);

		add(title, BorderLayout.NORTH);
		add(s, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		pack();
		setVisible(true);

	}
}
