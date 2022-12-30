package GUI;

import beans.User;
import dbutils.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Register {
    JPanel panel1,panel2,panel3,panel4;
    JLabel jLabel1,jLabel2,jLabel3;
    JButton jButton1;
    JTextField jTextField;
    JPasswordField jPasswordField1,jPasswordField2;
    //用于接下来的判断
    int TextLength=10;
    int PasswordLength=10;
    public Register(){
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
        //设置背景图片
        ImageIcon icon = new ImageIcon("images/12.jpg");
        JLabel label = new JLabel(icon);
        label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        JFrame frame = new JFrame();
        frame.getLayeredPane().add(label,new Integer((Integer.MIN_VALUE)));
        JPanel j = (JPanel)frame.getContentPane();
        j.setOpaque(false);

        jLabel1=new JLabel("        账号");
        jLabel1.setFont(new Font("方正粗黑宋简体",Font.PLAIN,20));
        jLabel2=new JLabel("        密码");
        jLabel2.setFont(new Font("方正粗黑宋简体",Font.PLAIN,20));
        jLabel3=new JLabel("确认密码");
        jLabel3.setFont(new Font("方正粗黑宋简体",Font.PLAIN,20));

        //设置Label字体颜色
        jLabel1.setForeground(Color.white);
        jLabel2.setForeground(Color.white);
        jLabel3.setForeground(Color.white);

        jButton1=new JButton("确定");

        jTextField=new JTextField(10);
        jPasswordField1=new JPasswordField(10);
        jPasswordField2=new JPasswordField(10);

        panel1=new JPanel();
        panel2=new JPanel();
        panel3=new JPanel();
        panel4=new JPanel();
        //将panel们设置为透明
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);

        panel1.add(jLabel1);
        panel1.add(jTextField);

        panel2.add(jLabel2);
        panel2.add(jPasswordField1);

        panel4.add(jLabel3);
        panel4.add(jPasswordField2);

        //panel3.add(jLabel3);
        panel3.add(jButton1);

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel4);
        frame.add(panel3);

        frame.setLayout(new GridLayout(4,1));
        frame.setTitle("用户注册");
        frame.setBounds(600,300,500,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ResultSet rs=null;
                Connection conn=null;
                PreparedStatement stmt=null;
                //获取JTextField1文本框内容
                String userId = jTextField.getText();
                //获取JPasswordField1密码框内容
                String strName = null;
                try {
                    conn= DBHelper.getConn();
                    stmt=conn.prepareStatement("select userid from user where userid='"+userId+"';");
                    rs=stmt.executeQuery();
                    while(rs.next()) {
                        strName = String.valueOf(rs.getInt("userid"));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } finally {
                    DBHelper.closeAll(conn,stmt,rs);
                }

                if(jTextField.getText().trim().length()==0|| new String(jPasswordField1.getPassword()).trim().length()==0||new String(jPasswordField2.getPassword()).trim().length()==0){
                    //trim();用于删除头尾的空白符
                    JOptionPane.showMessageDialog(null,"用户账号或密码不允许为空","注册出错",JOptionPane.ERROR_MESSAGE);
                }
                else if(jTextField.getText().trim().length()>TextLength||new String(jPasswordField1.getPassword()).trim().length()>PasswordLength||new String(jPasswordField2.getPassword()).trim().length()>PasswordLength){
                    JOptionPane.showMessageDialog(null,"用户或密码超出指定位数","注册出错",JOptionPane.ERROR_MESSAGE);
                }
                else if(jTextField.getText().trim().equals(strName)){
                        JOptionPane.showMessageDialog(null,"账号已存在","注册出错",JOptionPane.ERROR_MESSAGE);
                }
                else if(!(new String(jPasswordField1.getPassword()).equals(new String(jPasswordField2.getPassword())))){
                    JOptionPane.showMessageDialog(null,"与原密码不符合","注册出错",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    new User().User_Register(Integer.parseInt(jTextField.getText()),new String(jPasswordField1.getPassword()));
                    JOptionPane.showMessageDialog(null,"注册成功!");
                }

            }
        });

    }

}
