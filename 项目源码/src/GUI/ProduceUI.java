package GUI;

import dao.impl.LogDaoimpl;
import dbutils.DBHelper;

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

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ProduceUI extends JPanel {
    public DefaultTableModel defaultModel;
    public JTable jtable;
    public JButton yButton, newButton,flush;
    public JLabel shopname, shopid, shopnum, shopprice, shoptype,upshopname,shopcount;
    public JTextField shopname_f, shopid_f, shopnum_f, shopprice_f, shoptype_f,upshopname_f,shopcount_f;
    Connection conn = null;
    PreparedStatement stat = null;
    ResultSet rs = null;
    String id = null;
    String goodsname = null;
    String count = null;
    String price = null;
    String type = null;
    public ProduceUI() {

        setLayout(new BorderLayout());
        // 创建表格
        Object[] names = { "商品编号", "商品名称", "商品数量", "商品单价", "商品类型" };
        Object[][] data = new String[50][names.length];
        defaultModel = new DefaultTableModel(data, names);// 创建表格
        jtable = new JTable(defaultModel);
        //jtable.setPreferredScrollableViewportSize(new Dimension(200, 200));
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
            stat =  conn.prepareStatement("SELECT * FROM goodsinfo where count<='"+20+"';");
            rs = stat.executeQuery();
            while (rs.next()){
                 id = rs.getString("no");
                 goodsname = rs.getString("goodsname");
                 count = rs.getString("count");
                 price = rs.getString("price");
                 type = rs.getString("type");
                jtable.setValueAt(id,i,0);
                jtable.setValueAt(goodsname,i,1);
                jtable.setValueAt(count,i,2);
                jtable.setValueAt(price,i,3);
                jtable.setValueAt(type,i,4);
                i++;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        // 组装销售系统界面
        JPanel p1 = new JPanel();
        newButton = new JButton("增加新产品");
        p1.add(newButton);

        JPanel p2_left = new JPanel();
        yButton = new JButton("确认生产");
        p2_left.add(yButton);

        JPanel reFlushPanel = new JPanel();
        flush = new JButton("刷新");
        reFlushPanel.add(flush);

        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4=new JPanel();
        JPanel panel5=new JPanel();
        JPanel panel6=new JPanel();


        JPanel temppanel1 = new JPanel();
        JPanel temppanel2 = new JPanel();
        JPanel temppanel3 = new JPanel();
        JPanel temppanel4 = new JPanel();
        JPanel temppanel5 = new JPanel();
        JPanel temppanel_left1= new JPanel();
        JPanel temppanel_left2 = new JPanel();


        shopname = new JLabel("商品名称");
        shopid = new JLabel("商品编号");
        shopnum = new JLabel("商品数量");
        shopprice = new JLabel("商品价格");
        shoptype = new JLabel("商品类型");

        upshopname = new JLabel("商品名称");
        shopcount = new JLabel("商品数量");

        shopname_f = new JTextField(10);
        shopid_f = new JTextField(10);
        shopid_f.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!(ch >= '0' && ch <= '9')) {
                    e.consume();
                }
            }
        });
        shopnum_f = new JTextField(10);
        shopnum_f.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!(ch >= '0' && ch <= '9')) {
                    e.consume();
                }
            }
        });
        shopprice_f = new JTextField(10);
        shopprice_f.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!(ch >= '0' && ch <= '9')) {
                    e.consume();
                }
            }
        });
        shoptype_f = new JTextField(10);

        upshopname_f = new JTextField(10);
        shopcount_f = new JTextField(10);
        shopcount_f.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!(ch >= '0' && ch <= '9')) {
                    e.consume();
                }
            }
        });

        temppanel1.add(shopname);
        temppanel1.add(shopname_f);
        temppanel2.add(shopid);
        temppanel2.add(shopid_f);
        temppanel3.add(shopnum);
        temppanel3.add(shopnum_f);
        temppanel4.add(shopprice);
        temppanel4.add(shopprice_f);
        temppanel5.add(shoptype);
        temppanel5.add(shoptype_f);

        temppanel_left1.add(upshopname);
        temppanel_left1.add(upshopname_f);
        temppanel_left2.add(shopcount);
        temppanel_left2.add(shopcount_f);

        panel2.add(temppanel1);
        panel3.add(temppanel2);
        panel4.add(temppanel3);
        panel5.add(temppanel4);
        panel6.add(temppanel5);


        JPanel left = new JPanel();
        left.setLayout(new GridLayout(6,1));
        left.add(temppanel_left1);
        left.add(temppanel_left2);
        left.add(p2_left);
        left.add(reFlushPanel);

        JPanel sump = new JPanel();
        sump.setLayout(new GridLayout(6,1));
        sump.add(panel2);
        sump.add(panel3);
        sump.add(panel4);
        sump.add(panel5);
        sump.add(panel6);
        sump.add(p1);

        add(s, BorderLayout.CENTER);
        add(sump, BorderLayout.EAST);
        add(left,BorderLayout.WEST);

        setVisible(true);
        //jtable.setBackground(Color.pink);
        //事件监听
        yButton.addActionListener(new ProduceUIAction(this));
        newButton.addActionListener(new ProduceUIAction(this));
        flush.addActionListener(new ProduceUIAction(this));

    }
}
class ProduceUIAction implements ActionListener{
    private ProduceUI produceUI;
    public ProduceUIAction(ProduceUI produceUI){
        this.produceUI = produceUI;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //更新数量左边
        String name_left = produceUI.upshopname_f.getText();
        String count_left = produceUI.shopcount_f.getText();
        //添加商品右边
        String name = produceUI.shopname_f.getText();
        String id = produceUI.shopid_f.getText();
        String count = produceUI.shopnum_f.getText();
        String price = produceUI.shopprice_f.getText();
        String type = produceUI.shoptype_f.getText();

        if(e.getSource()==produceUI.yButton){
            if(name_left.equals("")|count_left.equals("")){
                JOptionPane.showMessageDialog(null,"输入不能为空！请重新输入。");
            }else{
                try {
                    int n1 = Integer.parseInt(count_left);
                    produceUI.stat =  produceUI.conn.prepareStatement("update goodsinfo set count=count+'"+n1+"' where goodsname='"+name_left+"';");
                    produceUI.stat.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"进货"+name_left+"商品"+count_left+"件。");
                //日志
                new LogDaoimpl().updateLog(produceUI.shoptype_f.getText(),name_left,"生产库存不足商品",id,count_left);
            }

        }else if(e.getSource()==produceUI.newButton){
        	boolean k = true;
            if(name.equals("")|id.equals("")|count.equals("")|price.equals("")|type.equals("")){
                JOptionPane.showMessageDialog(null,"输入不能为空！请重新输入。");
            }else{
                try {
                    produceUI.stat = produceUI.conn.prepareStatement("insert goodsinfo set no='"+id+"',goodsname='"+name+"',count='"+count+"',price='"+price+"',type='"+type+"';");
                    produceUI.stat.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"商品已存在！");
                    k = false;
                }if(k) {
                	JOptionPane.showMessageDialog(null,"进货"+name+"新商品"+count+"件。");
                    //日志
                    new LogDaoimpl().updateLog(type,name,"进货新商品",id,count);
                }
                
            }

        }else if(e.getSource()==produceUI.flush){//刷新
            for (int i = 0; i < produceUI.defaultModel.getRowCount(); i++) {
                for (int j = 0; j < produceUI.defaultModel.getColumnCount(); j++) {
                    produceUI.defaultModel.setValueAt("", i, j);
                }
            }
            try{
                int i = 0;
                produceUI.conn = DBHelper.getConn();
                produceUI.stat =  produceUI.conn.prepareStatement("SELECT * FROM goodsinfo where count<='"+20+"';");
                produceUI.rs = produceUI.stat.executeQuery();
                while (produceUI.rs.next()){
                     produceUI.id = produceUI.rs.getString("no");
                     produceUI.goodsname = produceUI.rs.getString("goodsname");
                     produceUI.count = produceUI.rs.getString("count");
                     produceUI.price = produceUI.rs.getString("price");
                     produceUI.type = produceUI.rs.getString("type");
                    produceUI.jtable.setValueAt(produceUI.id,i,0);
                    produceUI.jtable.setValueAt(produceUI.goodsname,i,1);
                    produceUI.jtable.setValueAt(produceUI.count,i,2);
                    produceUI.jtable.setValueAt(produceUI.price,i,3);
                    produceUI.jtable.setValueAt(produceUI.type,i,4);
                    i++;
                }

            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}

