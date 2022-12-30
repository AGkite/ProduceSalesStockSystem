package GUI;

import dbutils.DBHelper;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class LogUI extends JPanel {

    public DefaultTableModel defaultModel;
    public JTable jtable;
    Connection conn = null;
    PreparedStatement stat = null;
    ResultSet rs = null;

    public LogUI() {
        setLayout(new BorderLayout());
        // 创建表格
        Object[] names = { "商品名称", "商品编号", "商品状态", "商品数量", "商品类型","操作时间"};
        Object[][] data = new String[30][names.length];
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
            stat =  conn.prepareStatement("SELECT * FROM logs;");
            rs = stat.executeQuery();
            while (rs.next()){
                String name = rs.getString("ShopName");
                String id = rs.getString("ShopId");
                String State = rs.getString("State");
                String Numble = rs.getString("count");
                String type = rs.getString("type");
                String times = rs.getString("times");
                jtable.setValueAt(name,i,0);
                jtable.setValueAt(id,i,1);
                jtable.setValueAt(State,i,2);
                jtable.setValueAt(Numble,i,3);
                jtable.setValueAt(type,i,4);
                jtable.setValueAt(times,i,5);
                i++;
            }}
        catch(SQLException e){
            e.printStackTrace();
        }

        add(s, BorderLayout.CENTER);

        setVisible(true);

    }
}