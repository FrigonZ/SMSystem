package main.ui;

/*
主界面
*/

import java.awt.event.*;
import java.io.File;
import java.awt.*;
import javax.swing.*;



import main.Main;
import main.tcp.client.UploadClient;

public class MainFrame extends JFrame{

    private static final long serialVersionUID = 1L;
    JButton btExit;
    JButton btMini;
    JButton btMax;
    JButton btRe;
    JButton btChat;
    JButton btFile;
    JButton btRate;
    JButton btInfo;
    JButton btLog;
    ImagePanel ipTitle;
    JPanel pnLeft;
    JPanel pnRight;
    ChatPanel pnChat;
    FilePanel pnFile;
    InfoPanel pnInfo;
    RatePanel pnRate;
    ImageIcon iiChatb,iiChat;
    ImageIcon iiInfob,iiInfo;
    ImageIcon iiFileb,iiFile;
    ImageIcon iiRateb,iiRate;
    int width = 1200;
    int height = 800;
    int preX = 0;
    int preY = 0;

    public TextArea getTa(){
        return pnChat.getTa();
    }

    public MainFrame() {

        setSize(1200, 800);
        setLayout(null);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        //实现拖拽（记录鼠标位移）
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                preX = e.getX();
                preY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int nowX = e.getXOnScreen();
                int nowY = e.getYOnScreen();
                setLocation(nowX - preX, nowY - preY);
            }
        });

        //设置logo
        ImageIcon logo = new ImageIcon("src//main//res//icons//logo.png");
        setIconImage(logo.getImage());

        //菜单栏
        ipTitle = new ImagePanel(new ImageIcon("src//main//res//images//title.png"));
        ipTitle.setSize(width, 100);
        ipTitle.setLocation(0, 0);

        //左侧导航栏
        pnLeft = new JPanel();
        pnLeft.setSize(75, height);
        pnLeft.setLocation(0, 100);
        pnLeft.setBackground(new Color(18, 150, 219));
        pnLeft.setLayout(null);

        //聊天模块
        pnChat = new ChatPanel(getWidth()-75,getHeight()-100);

        //通知模块
        pnInfo = new InfoPanel(getWidth()-75,getHeight()-100);

        //文件模块
        pnFile = new FilePanel(getWidth()-75,getHeight()-100);

        //投票模块
        pnRate = new RatePanel(getWidth()-75,getHeight()-100);

        //右侧主界面使用 cardlayout 添加四个模块
        CardLayout c = new CardLayout();
        pnRight = new JPanel(c);
        pnRight.setSize(width-75,height-100);
        pnRight.setLocation(75, 100);
        pnRight.add("chat",pnChat);
        pnRight.add("info",pnInfo);
        pnRight.add("file",pnFile);
        pnRight.add("rate",pnRate);

        //左侧导航栏按钮icon
        iiChatb = new ImageIcon("src//main//res//icons//chat_b.png");
        iiChat = new ImageIcon("src//main//res//icons//chat.png");
        iiInfob = new ImageIcon("src//main//res//icons//info_b.png");
        iiInfo = new ImageIcon("src//main//res//icons//info.png");
        iiFileb = new ImageIcon("src//main//res//icons//file_b.png");
        iiFile = new ImageIcon("src//main//res//icons//file.png");
        iiRateb = new ImageIcon("src//main//res//icons//rate_b.png");
        iiRate = new ImageIcon("src//main//res//icons//rate.png");

        /*
        以下为四个左侧导航栏按钮
        使用iconbutton类（main.ui.iconbutton）
        作用是切换右侧卡片布局
        在点击事件后改变默认button图标以表示当前页面
        */
        //切换至聊天（默认）
        btChat = new IconButton(iiChat,iiChat);
        btChat.setSize(45, 45);
        btChat.setLocation(15, 15);
        btChat.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                c.show(pnRight,"chat");
                btChat.setIcon(iiChat);
                btInfo.setIcon(iiInfob);
                btFile.setIcon(iiFileb);
                btRate.setIcon(iiRateb);
            }
        });

        //切换至通知
        btInfo = new IconButton(iiInfob,iiInfo);
        btInfo.setSize(45, 45);
        btInfo.setLocation(15, 75);
        btInfo.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                pnInfo.update();
                c.show(pnRight,"info");
                btChat.setIcon(iiChatb);
                btInfo.setIcon(iiInfo);
                btFile.setIcon(iiFileb);
                btRate.setIcon(iiRateb);
            }
        });

        //切换至文件
        btFile = new IconButton(iiFileb,iiFile);
        btFile.setSize(45, 45);
        btFile.setLocation(15, 135);
        btFile.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                c.show(pnRight,"file");
                btChat.setIcon(iiChatb);
                btInfo.setIcon(iiInfob);
                btFile.setIcon(iiFile);
                btRate.setIcon(iiRateb);
            }
        });

        //切换至投票
        btRate = new IconButton(iiRateb,iiRate);
        btRate.setSize(45, 45);
        btRate.setLocation(15, 195);
        btRate.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                pnRate.update();
                c.show(pnRight,"rate");
                btChat.setIcon(iiChatb);
                btInfo.setIcon(iiInfob);
                btFile.setIcon(iiFileb);
                btRate.setIcon(iiRate);
            }
        });

        //左侧导航栏退出账号按钮
        btLog = new IconButton(new ImageIcon("src//main//res//icons//out_b.png"),new ImageIcon("src//main//res//icons//out.png"));
        btLog.setSize(45, 45);
        btLog.setLocation(15, 255);
        btLog.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LogFrame.on();
            }
        });

        /*
        以下四个按钮为菜单栏右上的最小化，最大化，还原，关闭按钮
        使用iconbutton类
        实现最大化与还原按钮之间的交换
        */

        //关闭
        btExit = new IconButton(new ImageIcon("src//main//res//icons//exit.png"), new ImageIcon("src//main//res//icons//exit_red.png"));
        btExit.setBounds(width-30, 0, 30, 30);
        btExit.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //最大化
        btMax = new IconButton(new ImageIcon("src//main//res//icons//max.png"), new ImageIcon("src//main//res//icons//max_red.png"));
        btMax.setBounds(width-60, 0, 30, 30);
        btMax.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
                //切换还原与最大化
                btMax.setVisible(false);
                btRe.setVisible(true);
                reFresh();//用于调整组件大小
            }
        });

        //还原
        btRe = new IconButton(new ImageIcon("src//main//res//icons//re.png"), new ImageIcon("src//main//res//icons//re_red.png"));
        btRe.setBounds(width-60, 0, 30, 30);
        btRe.setVisible(false);
        btRe.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                //切换还原与最大化
                btRe.setVisible(false);
                btMax.setVisible(true);
                setSize(1200,800);//原始大小1200*800
                reFresh();//用于调整组件大小
            }
        });

        //最小化
        btMini = new IconButton(new ImageIcon("src//main//res//icons//mini.png"), new ImageIcon("src//main//res//icons//mini_red.png"));
        btMini.setBounds(width-90, 0, 30, 30);
        btMini.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                setExtendedState(JFrame.ICONIFIED);
            }
        });

        add(ipTitle);
        add(pnLeft);
        add(pnRight);

        ipTitle.add(btExit);
        ipTitle.add(btMax);
        ipTitle.add(btRe);
        ipTitle.add(btMini);

        pnLeft.add(btChat);
        pnLeft.add(btInfo);
        pnLeft.add(btFile);
        pnLeft.add(btRate);
        pnLeft.add(btLog);
    }

    public static void on() {
        //显示主界面
        new MainFrame().setVisible(true);
    }

    public void reFresh(){
        //更改组件大小及位置，以实现适应效果
        width = getWidth();
        height = getHeight();
        ipTitle.setSize(width,100);
        pnLeft.setSize(75, height);
        btExit.setLocation(width-30, 0);
        btRe.setLocation(width-60, 0);
        btMax.setLocation(width-60, 0);
        btMini.setLocation(width-90, 0);
        pnChat.setSize(getWidth()-75,getHeight()-100);
        pnChat.reFresh();
        pnInfo.setSize(getWidth()-75,getHeight()-100);
        pnInfo.reFresh();
        pnFile.setSize(getWidth()-75,getHeight()-100);
        pnFile.reFresh();
        pnRate.setSize(getWidth()-75,getHeight()-100);
        pnRate.reFresh();
    }    

    public static void main(String[] args) {
        
        new MainFrame().setVisible(true);
    }
}


class ChatPanel extends JPanel{

    //聊天模块
    private static final long serialVersionUID = 1L;
    TextArea ta;
    TextField tf;
    JButton btSend;
    FriendList fl;

    private void sendMsg(String msg){
        Main.cc.sendMsg(msg);
        tf.setText("");
    }

    public ChatPanel(int x,int y){

        setSize(x,y);
        setLayout(null);

        ta = new TextArea();
        ta.setEditable(false);
        ta.setBounds(0, 0, getWidth()-160, 3*getHeight()/4);

        tf = new TextField();
        tf.setBounds(0, 3*getHeight()/4+10, getWidth()-200, getHeight()/4-20);

        btSend = new JButton("send");
        btSend.setBounds(getWidth()-190, 3*getHeight()/4+10, 30, getHeight()/4-20);
        btSend.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tf.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "内容不可为空", "msg", JOptionPane.WARNING_MESSAGE);
                }else{
                    String msg = tf.getText().toString();
                    sendMsg(msg);
                }
            }
        });

        fl = new FriendList();
        fl.getList(Main.account);
        fl.setBounds(getWidth()-150, 0, 150, getHeight());

        add(ta);
        add(tf);
        add(btSend);
        add(fl);
    }

    public TextArea getTa(){
        return ta;
    }

    public void reFresh(){
        //组件调整
        ta.setBounds(0, 0, getWidth()-160, 3*getHeight()/4);
        tf.setBounds(0, 3*getHeight()/4+10, getWidth()-200, getHeight()/4-20);
        btSend.setBounds(getWidth()-190, 3*getHeight()/4+10, 30, getHeight()/4-20);
        fl.setBounds(getWidth()-150, 0, 150, getHeight());
    }
}

class FilePanel extends JPanel{

    //文件模块
    private static final long serialVersionUID = 1L;
    IconButton btUp,btDown;
    
    public FilePanel(int x,int y){
        setSize(x,y);
        setLayout(null);
        ImageIcon iiUp = new ImageIcon("src//main//res//icons//UPLOAD.png");
        btUp = new IconButton(iiUp);
        btUp.setBounds(0, 0, getWidth()/2, getHeight());
        btUp.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                File file = fileChooser.getSelectedFile();
                if(file != null){
                    new UploadClient(file);
                }
            }
        });
        ImageIcon iiDown = new ImageIcon("src//main//res//icons//DOWNLOAD.png");
        btDown = new IconButton(iiDown);
        btDown.setBounds(getWidth()/2, 0, getWidth()/2, getHeight());
        btDown.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                new FileFrame().setVisible(true);
            }
        });

        add(btUp);
        add(btDown);
    }

    public void reFresh(){
        btUp.setBounds(0, 0, getWidth()/2, getHeight());
        btDown.setBounds(getWidth()/2, 0, getWidth()/2, getHeight());
    }
}

class InfoPanel extends JPanel{

    //通知模块
    private static final long serialVersionUID = 1L;
    JPanel pnBot;
    InfoList pnTop;
    JButton btNew;

    public InfoPanel(int x,int y){

        setSize(x,y);
        setLayout(null);

        //顶部导航栏
        pnTop = new InfoList();
        pnTop.getList();
        pnTop.setSize(getWidth(),getHeight()-50);
        pnTop.setLocation(0, 0);

        pnBot = new JPanel();
        pnBot.setSize(getWidth(),50);
        pnBot.setLocation(0, getHeight()-50);


        btNew = new JButton("新通知");
        btNew.setSize(60, 30);
        btNew.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Main.admin)
                    new NoticeFrame().setVisible(true);
                else
                    JOptionPane.showMessageDialog(null, "admin only", "Wrong", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        pnBot.add(btNew);

        add(pnTop);
        add(pnBot);
    }

    public void update(){
        pnTop.getList();
    }

    public void reFresh(){
        //调整组件
        pnTop.setSize(getWidth(),getHeight()-50);
        pnTop.setLocation(0, 0);
        pnBot.setSize(getWidth(),50);
        pnBot.setLocation(0, getHeight()-50);
    }


}

class RatePanel extends JPanel{

    //投票模块
    //通知模块
    private static final long serialVersionUID = 1L;
    JPanel pnBot;
    VoteList pnTop;
    JButton btNew;

    public RatePanel(int x,int y){

        setSize(x,y);
        setLayout(null);

        pnTop = new VoteList();
        pnTop.getList();
        pnTop.setSize(getWidth(),getHeight()-50);
        pnTop.setLocation(0, 0);

        pnBot = new JPanel();
        pnBot.setSize(getWidth(),50);
        pnBot.setLocation(0, getHeight()-50);


        btNew = new JButton("新投票");
        btNew.setSize(60, 30);
        btNew.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Main.admin)
                    new VoteFrame().setVisible(true);
                else
                    JOptionPane.showMessageDialog(null, "admin only", "Wrong", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        pnBot.add(btNew);

        add(pnTop);
        add(pnBot);
    }

    public void update(){
        pnTop.getList();
    }

    public void reFresh(){
        //调整组件
        pnTop.setSize(getWidth(),getHeight()-50);
        pnTop.setLocation(0, 0);
        pnBot.setSize(getWidth(),50);
        pnBot.setLocation(0, getHeight()-50);
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}