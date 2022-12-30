package GUI;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.*;

public class TcpServerUI extends JFrame{
    private static final long serialVersionUID = 1L;
    JTextArea mainArea;
    JTextArea sendArea;
    JTextField indexArea;
    JButton sendButtn;
    ServerCom serverCom;

    public void setServer(ServerCom serverCom) {
        this.serverCom=serverCom;
    }

    public TcpServerUI() {
        super("大帝百货客服中心");
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
                serverCom.sendMsg(sendArea.getText());
                mainArea.append("【小艾客服】" + sendArea.getText() + "\n");
                sendArea.setText("");
            }
        });
        JPanel tempPaanel=new JPanel();
        indexArea=new JTextField(2);
        indexArea.setText("0");
        tempPaanel.add(sendButtn);
        tempPaanel.add(indexArea);
        panel.add(tempPaanel,BorderLayout.EAST);
        panel.add(sendArea,BorderLayout.CENTER);
        contain.add(mainAreaP,BorderLayout.CENTER);
        contain.add(panel,BorderLayout.SOUTH);
        this.setBounds(20,450,300,250);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
class ServerCom extends Thread{
    Socket socket;
    ServerSocket serverSocket ;
    BufferedReader in;
    PrintWriter out;
    TcpServerUI ui;

    public  ServerCom(TcpServerUI ui) {
        this.ui=ui;
        ui.setServer(this);
        try {
            serverSocket = new ServerSocket(1022);
            //System.out.println("启动服务器成功，等待端口号：1022");
            ui.mainArea.append("客服已上班，等待用户呼叫。\n");
            socket = serverSocket.accept();
            //System.out.println("连接成功！来自" + socket.toString());
            ui.mainArea.append("用户连接成功！\n");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }catch (Exception e) {
            //JOptionPane.showMessageDialog(null,""+e);
        }
        start();
    }
    @Override
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
            }
            if (msg != null && msg.trim() != "") {
                //System.out.println(">>" + msg);
                ui.mainArea.append(msg + "\n");
            }
        }
    }
    public void sendMsg(String msg) {
        try {
            out.println("【小艾客服】" + msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,""+e);
        }
    }

}









