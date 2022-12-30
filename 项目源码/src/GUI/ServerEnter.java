package GUI;

import dbutils.DBHelper;

import static javax.swing.JOptionPane.CANCEL_OPTION;
import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ServerEnter {
    JPanel JPanel1,JPanel2,JPanel3;
    JTextField JTextField1;
    JLabel JLabel1,JLabel2;
    JPasswordField JPasswordField1;
    JButton JButton1,JButton2,JButton3,JButton4;

    //用于接下来的判断
    int TextLength=10;
    int PasswordLength=10;
    public ServerEnter(){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //加载图片
        ImageIcon icon=new ImageIcon("images/11.png");
        //Image im=new Image(icon);
        //将图片放入label中
        JLabel label=new JLabel(icon);

        //设置label的大小
        label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

        JFrame frame=new JFrame();

        //获取窗口的第二层，将label放入
        frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));

        //获取frame的顶层容器,并设置为透明
        JPanel j=(JPanel)frame.getContentPane();
        j.setOpaque(false);

        //必须设置为透明的。否则看不到图片

        JPanel1=new JPanel();

        JPanel2=new JPanel();

        JPanel3=new JPanel();


        JLabel1=new JLabel("账号");
        JLabel1.setFont(new Font("方正粗黑宋简体",Font.PLAIN,20));
        JLabel2=new JLabel("密码");
        JLabel2.setFont(new Font("方正粗黑宋简体",Font.PLAIN,20));
        //设置Label字体颜色
        JLabel1.setForeground(Color.white);
        JLabel2.setForeground(Color.white);

        JButton1=new JButton("登录");
        JButton2=new JButton("退出");
        JButton3=new JButton("商家注册");
        //JButton4=new JButton("个人注册");

        //设计对话框及密码框的长度
        JTextField1=new JTextField(10);
        JPasswordField1=new JPasswordField(10);

        //网格布局三行一列
        frame.setLayout(new GridLayout(3,1));

        //向JFrame加入三个JPanel

        frame.add(JPanel1);
        frame.add(JPanel2);
        frame.add(JPanel3);

        //向第一个JPanel添加JLabel和JTextField
        JPanel1.add(JLabel1);
        JPanel1.add(JTextField1);

        //向第一个JPanel添加JLabel和JPasswordField
        JPanel2.add(JLabel2);
        JPanel2.add(JPasswordField1);

        //向第三个JPanel加入两个JButton
        JPanel3.add(JButton1);
        JPanel3.add(JButton3);
        //JPanel3.add(JButton4);
        JPanel3.add(JButton2);

        frame.setTitle("商家登录界面");
        frame.setSize(411,240);
        frame.setLocation(500,100);
        //关闭界面
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置界面可显示
        frame.setVisible(true);
        JButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ResultSet rs=null;
                Statement stmt=null;
                Connection conn=null;
                PreparedStatement ps=null;
                String userId = JTextField1.getText();
                String pwd=new String(JPasswordField1.getPassword());
                String strName = null;
                String password=null;
                String buy=null;
                try {
                    conn= DBHelper.getConn();
                    stmt=conn.createStatement();
                    rs=stmt.executeQuery("select userid ,userpassword,buy from user where userid='"+userId+"'and userpassword='"+md5Hex(pwd)+"';");

                    while(rs.next()) {
                        strName = String.valueOf(rs.getInt("userid"));
                        password=rs.getString("userpassword");
                        buy=rs.getString("buy");
                    }
                } catch (SQLException e1) {
                    // TODO 自动生成的 catch 块
                    e1.printStackTrace();
                } finally {
                    DBHelper.closeAll(conn,ps,rs);
                }

                if(JTextField1.getText().trim().length()==0|| new String(JPasswordField1.getPassword()).trim().length()==0){
                //trim();用于删除头尾的空白符
                JOptionPane.showMessageDialog(null,"用户密码不允许为空","登录出错",JOptionPane.ERROR_MESSAGE);
            }
             else if(JTextField1.getText().trim().length()>TextLength||new String(JPasswordField1.getPassword()).trim().length()>PasswordLength){
                JOptionPane.showMessageDialog(null,"用户或密码超出指定位数","登录出错",JOptionPane.WARNING_MESSAGE);
            }
             else if(!(userId.trim().equals(strName)&& password.trim().equals(md5Hex(pwd)))){
                 JOptionPane.showMessageDialog(null,"用户名或密码错误","登录出错",JOptionPane.ERROR_MESSAGE);
             }
            else if(buy==null&&userId.trim().equals(strName)&& password.trim().equals(md5Hex(pwd))){
                        new MainMenu();
                }
            else if(buy.equals("yes")&&userId.trim().equals(strName)&& password.trim().equals(md5Hex(pwd))){
                new UserUI(userId);
        }
            else {
                JOptionPane.showMessageDialog(null,"用户名或密码错误","登录出错",JOptionPane.QUESTION_MESSAGE);
            }
        }
        });
           JButton2.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent actionEvent) {
               //关闭窗口释放屏幕资源
                   int num = JOptionPane.showConfirmDialog(null,"您确定要退出产销存系统吗？","提示",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                   switch (num){
                       case YES_OPTION :
                           System.exit(0);
                           break;
                       case NO_OPTION:
                           break;
                       case CANCEL_OPTION:
                           break;
                   }
               }
           });
           JButton3.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent actionEvent) {
                   new Register();
               }
           });

        JPanel1.setOpaque(false);
        JPanel2.setOpaque(false);
        JPanel3.setOpaque(false);
    }
}