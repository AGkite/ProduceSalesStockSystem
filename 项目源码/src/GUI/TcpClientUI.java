package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.*;

public class TcpClientUI extends JFrame{
    private static final long serialVersionUID = -4688023073182003288L;
    JTextArea mainArea;
    JTextArea sendArea;
    JButton sendButtn;
    ClientCom clientCom;
    JButton btnLink;
    JTextField ipTextField;

    public void setCilent(ClientCom clientCom) {
        this.clientCom=clientCom;
    }

    public TcpClientUI() {
        super("大帝百货用户中心");

        Container contain=getContentPane();
        contain.setLayout(new BorderLayout());
        mainArea=new JTextArea();
        JScrollPane mainAreaP=new JScrollPane(mainArea);

        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        sendArea=new JTextArea(3,8);
        sendButtn=new JButton("发送");

        sendButtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(clientCom!=null){
                    clientCom.sendMsg(sendArea.getText());
                    mainArea.append("【用户】" + sendArea.getText() + "\n");
                    sendArea.setText("");
                }else{
                    JOptionPane.showMessageDialog(null,"请先联系上客服哟！");
                }
            }
        });

        ipTextField=new JTextField(12);
        ipTextField.setText("192.168.31.24");
        btnLink = new JButton("连接");
        btnLink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientCom = new ClientCom(TcpClientUI.this,ipTextField.getText());
                TcpClientUI.this.setCilent(clientCom);
            }
        });

        JPanel ipPaanel=new JPanel();
        ipPaanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        ipPaanel .add(new JLabel("服务器"));
        ipPaanel.add(ipTextField);
        ipPaanel.add(btnLink);


        panel.add(sendButtn,BorderLayout.EAST);
        panel.add(sendArea,BorderLayout.CENTER);
        panel.add(ipPaanel,BorderLayout.NORTH);
        contain.add(mainAreaP,BorderLayout.CENTER);
        contain.add(panel,BorderLayout.SOUTH);
        this.setBounds(20,200,300,250);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}

class ClientCom extends Thread{
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    TcpClientUI tcpClientUI;

    public ClientCom(TcpClientUI ui,String ip) {
        this.tcpClientUI=ui;
        try {
            socket=new Socket(ip,1022);
            //System.out.println("已顺利连接到服务器。");
            tcpClientUI.mainArea.append("已顺利呼叫上小艾客服啦。\n");
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"客服不在线呢！亲！您可以选择留言哦！");
        }
        start();
    }


    public void run() {
        String msg = "";
        while (true) {
            try {
                msg = in.readLine();
            } catch (SocketException ex) {
                //JOptionPane.showMessageDialog(null,""+ex);
                break;
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null,""+ex);
                break;
            }
            if (msg != null && msg.trim() != "") {
                //System.out.println(">>" + msg);
                tcpClientUI.mainArea.append(msg + "\n");
            }
        }
    }

    public void sendMsg(String msg) {
        try {
            out.println("【用户】" + msg);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"请先联系上客服哟！");
        }
    }
}


