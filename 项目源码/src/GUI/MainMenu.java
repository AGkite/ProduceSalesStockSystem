package GUI;

import dao.impl.GoodsDaoimpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class MainMenu extends JFrame {

    public JPanel panel1,panel2,cardPanel,panel4;
    public JButton produce,sales,stock,log,outLog,send,say,vip;
    public JLabel title;
    public Font font1;
    public CardLayout card;

    public MainMenu() {
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

        setTitle("产销存一体化信息系统");
        setBounds(300,200,1000,500);
        setLayout(new BorderLayout());
        font1 = new Font("华文行楷",Font.PLAIN,36);
        title = new JLabel("大帝百货一体化信息系统");
        title.setFont(font1);
        panel1 = new JPanel();
        panel2 = new JPanel();
        cardPanel = new JPanel();
        panel4 = new JPanel();
        sales = new JButton("销售系统");
        produce = new JButton("生产系统");
        stock = new JButton("库存系统");
        log = new JButton("日志");
        outLog = new JButton("导出日志");
        send=new JButton("留言");
        say=new JButton("客服中心");
        vip=new JButton("会员");

        Color color = new Color(166,255,249);
        sales.setBackground(color);
        produce.setBackground(color);
        stock.setBackground(color);
        log.setBackground(color);
        outLog.setBackground(color);
        send.setBackground(color);
        say.setBackground(color);
        vip.setBackground(color);

        panel1.add(title);

        panel2.setLayout(new GridLayout(10,1));
        panel2.add(sales);
        panel2.add(produce);
        panel2.add(stock);
        panel2.add(log);
        panel2.add(outLog);
        panel2.add(send);
        panel2.add(vip);
        panel2.add(say);

        send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf1 = new JFrame();
				jf1.setTitle("用户的留言");
				JTextArea jt1 = new JTextArea(15,30);
				JButton send = new JButton("发送");
				//jf1.setLocationRelativeTo(null);
				jf1.add(jt1, BorderLayout.NORTH);
//				jf1.add(send, BorderLayout.SOUTH);
				jt1.setLineWrap(true); // 激活自动换行功能
				jf1.pack();
				jf1.setVisible(true);
				ArrayList<String> ary= new GoodsDaoimpl().gettalk();
				jt1.setText("内容\t"+"时间\t");
				jt1.append("\n");
				for(String s:ary) 
				{
					jt1.append(s);jt1.append("\n");
				}
			}
		});
        card = new CardLayout();
        cardPanel.setLayout(card);
        String [] cardName = {"0","1","2","3","4"};//用于卡片命名

        cardPanel.add(new SalesUI(), cardName[0]);
        cardPanel.add(new StockUI(), cardName[1]);
        cardPanel.add(new ProduceUI(), cardName[2]);
        cardPanel.add(new LogUI(), cardName[3]);
        cardPanel.add(new VipUI(), cardName[4]);

        panel4.setLayout(new BorderLayout());
        panel4.add(panel2,BorderLayout.WEST);
        panel4.add(cardPanel,BorderLayout.CENTER);

        add(panel1,BorderLayout.NORTH);
        add(panel4,BorderLayout.CENTER);
        //pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //事件注册
        sales.addActionListener(new MainMenuAction(this));
        stock.addActionListener(new MainMenuAction(this));
        produce.addActionListener(new MainMenuAction(this));
        log.addActionListener(new MainMenuAction(this));
        outLog.addActionListener(new MainMenuAction(this));
        say.addActionListener(new MainMenuAction(this));
        vip.addActionListener(new MainMenuAction(this));

    }
}
class MainMenuAction implements ActionListener{
    private MainMenu mainMenu;
    public MainMenuAction(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==mainMenu.sales){
            mainMenu.card.show(mainMenu.cardPanel,"0");
        }else if(e.getSource()==mainMenu.stock){
            mainMenu.card.show(mainMenu.cardPanel,"1");
        }else if(e.getSource()==mainMenu.produce){
            mainMenu.card.show(mainMenu.cardPanel,"2");
        }else if(e.getSource()==mainMenu.log){
            mainMenu.card.show(mainMenu.cardPanel,"3");
        }else if(e.getSource()==mainMenu.outLog){
            new OutputExcel();
        }else if(e.getSource()==mainMenu.say){
            JOptionPane.showMessageDialog(null,"客服已上线，等待用户连接！");
            TcpServerUI serverUI=new TcpServerUI();
            ServerCom server=new ServerCom(serverUI);
        }else if(e.getSource()==mainMenu.vip){
            mainMenu.card.show(mainMenu.cardPanel,"4");
        }
    }
}

