package main.frigon;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

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
    JPanel pnChat;
    JPanel pnFile;
    JPanel pnInfo;
    JPanel pnRate;
    ImageIcon iiChatb,iiChat;
    ImageIcon iiInfob,iiInfo;
    ImageIcon iiFileb,iiFile;
    ImageIcon iiRateb,iiRate;
    int width = 1200;
    int height = 800;
    int preX = 0;
    int preY = 0;

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

        ImageIcon logo = new ImageIcon("src//main//res//icons//logo.png");
        setIconImage(logo.getImage());

        ipTitle = new ImagePanel(new ImageIcon("src//main//res//images//title.png"));
        ipTitle.setSize(width, 100);
        ipTitle.setLocation(0, 0);

        pnLeft = new JPanel();
        pnLeft.setSize(75, height);
        pnLeft.setLocation(0, 100);
        pnLeft.setBackground(new Color(18, 150, 219));
        pnLeft.setLayout(null);

        pnChat = new JPanel();

        pnInfo = new JPanel();

        pnFile = new JPanel();

        pnRate = new JPanel();

        CardLayout c = new CardLayout();
        pnRight = new JPanel(c);
        pnRight.setSize(width-75,height-100);
        pnRight.setLocation(75, 100);
        pnRight.add("chat",pnChat);
        pnRight.add("info",pnInfo);
        pnRight.add("file",pnFile);
        pnRight.add("rate",pnRate);

        iiChatb = new ImageIcon("src//main//res//icons//chat_b.png");
        iiChat = new ImageIcon("src//main//res//icons//chat.png");
        iiInfob = new ImageIcon("src//main//res//icons//info_b.png");
        iiInfo = new ImageIcon("src//main//res//icons//info.png");
        iiFileb = new ImageIcon("src//main//res//icons//file_b.png");
        iiFile = new ImageIcon("src//main//res//icons//file.png");
        iiRateb = new ImageIcon("src//main//res//icons//rate_b.png");
        iiRate = new ImageIcon("src//main//res//icons//rate.png");

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

        btInfo = new IconButton(iiInfob,iiInfo);
        btInfo.setSize(45, 45);
        btInfo.setLocation(15, 75);
        btInfo.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                c.show(pnRight,"info");
                btChat.setIcon(iiChatb);
                btInfo.setIcon(iiInfo);
                btFile.setIcon(iiFileb);
                btRate.setIcon(iiRateb);
            }
        });

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

        btRate = new IconButton(iiRateb,iiRate);
        btRate.setSize(45, 45);
        btRate.setLocation(15, 195);
        btRate.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                c.show(pnRight,"rate");
                btChat.setIcon(iiChatb);
                btInfo.setIcon(iiInfob);
                btFile.setIcon(iiFileb);
                btRate.setIcon(iiRate);
            }
        });

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

        btExit = new IconButton(new ImageIcon("src//main//res//icons//exit.png"), new ImageIcon("src//main//res//icons//exit_red.png"));
        btExit.setBounds(width-30, 0, 30, 30);
        btExit.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btMax = new IconButton(new ImageIcon("src//main//res//icons//max.png"), new ImageIcon("src//main//res//icons//max_red.png"));
        btMax.setBounds(width-60, 0, 30, 30);
        btMax.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
                btMax.setVisible(false);
                btRe.setVisible(true);
                width = getWidth();
                height = getHeight();
                ipTitle.setSize(width,100);
                pnLeft.setSize(75, height);
                btExit.setLocation(width-30, 0);
                btRe.setLocation(width-60, 0);
                btMini.setLocation(width-90, 0);
            }
        });

        btRe = new IconButton(new ImageIcon("src//main//res//icons//re.png"), new ImageIcon("src//main//res//icons//re_red.png"));
        btRe.setBounds(width-60, 0, 30, 30);
        btRe.setVisible(false);
        btRe.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                btRe.setVisible(false);
                btMax.setVisible(true);
                setSize(1200,800);
                width = getWidth();
                height = getHeight();
                ipTitle.setSize(width,100);
                pnLeft.setSize(75, height);
                btExit.setLocation(width-30, 0);
                btMax.setLocation(width-60, 0);
                btMini.setLocation(width-90, 0);
            }
        });

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
        new MainFrame().setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
