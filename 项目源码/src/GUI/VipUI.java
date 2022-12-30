package GUI;

import dbutils.DBHelper;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



public class VipUI extends JPanel {

	JTable jtable;
	DefaultTableModel defaultModel;
	public JLabel title;
	public Font font;
	Connection conn = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
	int userid = 0;
	String userpassword = null;
	String adress = null;
	String phone = null;
	public VipUI() {
		// TODO 自动生成的构造函数存根
		setLayout(new BorderLayout());

		// 创建表格
		Object[] names = { "会员账号", "会员密码", "会员住址", "会员电话" };
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
		try{
			int i = 0;
			conn = DBHelper.getConn();
			stat =  conn.prepareStatement("SELECT * FROM user where buy='yes';");
			rs = stat.executeQuery();
			while (rs.next()){
				userid = rs.getInt("userid");
				userpassword = rs.getString("userpassword");
				adress = rs.getString("adress");
				phone = rs.getString("phone");
				jtable.setValueAt(userid,i,0);
				jtable.setValueAt(userpassword,i,1);
				jtable.setValueAt(adress,i,2);
				jtable.setValueAt(phone,i,3);
				i++;
			}

		}catch(SQLException e){
			e.printStackTrace();
		}
		font = new Font("华文行楷",Font.PLAIN,30);
        title = new JLabel("会员信息表");
        title.setFont(font);
        JPanel jPanel=new JPanel();
        jPanel.add(title);
		
        add(jPanel,BorderLayout.NORTH);
		add(s, BorderLayout.CENTER);
		setVisible(true);		
	}

}
