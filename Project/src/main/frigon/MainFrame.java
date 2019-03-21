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

        ipTitle = new ImagePanel(new ImageIcon("src//main//res//images//title.png"));
        ipTitle.setSize(width, 100);
        ipTitle.setLocation(0, 0);

        pnLeft = new JPanel();
        pnLeft.setSize(75, height);
        pnLeft.setLocation(0, 100);
        pnLeft.setBackground(new Color(18, 150, 219));
        pnLeft.setLayout(null);

        btChat = new IconButton(new ImageIcon("src//main//res//icons//chat_b.png"),new ImageIcon("src//main//res//icons//chat.png"));
        btChat.setSize(45, 45);
        btChat.setLocation(15, 15);

        btInfo = new IconButton(new ImageIcon("src//main//res//icons//info_b.png"),new ImageIcon("src//main//res//icons//info.png"));
        btInfo.setSize(45, 45);
        btInfo.setLocation(15, 75);

        btFile = new IconButton(new ImageIcon("src//main//res//icons//file_b.png"),new ImageIcon("src//main//res//icons//file.png"));
        btFile.setSize(45, 45);
        btFile.setLocation(15, 135);

        btRate = new IconButton(new ImageIcon("src//main//res//icons//rate_b.png"),new ImageIcon("src//main//res//icons//rate.png"));
        btRate.setSize(45, 45);
        btRate.setLocation(15, 195);

        pnRight = new JPanel();
        pnRight.setSize(width-75,height-100);
        pnRight.setLocation(75, 100);
        pnRight.setLayout(new CardLayout());

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
    }

    public static void on() {
        new MainFrame().setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
