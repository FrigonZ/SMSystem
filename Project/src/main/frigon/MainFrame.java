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
    int width = 1200;
    int height = 800;
    int preX = 0;
    int preY = 0;

    public MainFrame() {

        ImagePanel ipTitle = new ImagePanel(new ImageIcon("src//main//res//images//title.png"));
        ipTitle.setSize(1200, 100);

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

        ipTitle.setLocation(0, 0);
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

        btExit = new JButton();
        btExit.setIcon(new ImageIcon("src//main//res//icons//exit.png"));
        btExit.setRolloverIcon(new ImageIcon("src//main//res//icons//exit_red.png"));
        btExit.setBounds(width-30, 0, 30, 30);
        btExit.setMargin(new Insets(0, 0, 0, 0));
        btExit.setBorderPainted(false);
        btExit.setBorder(null);
        btExit.setContentAreaFilled(false);
        btExit.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btMax = new JButton();
        btMax.setBounds(width-60, 0, 30, 30);
        btMax.setIcon(new ImageIcon("src//main//res//icons//max.png"));
        btMax.setRolloverIcon(new ImageIcon("src//main//res//icons//max_red.png"));
        btMax.setMargin(new Insets(0, 0, 0, 0));
        btMax.setBorder(null);
        btMax.setContentAreaFilled(false);
        btMax.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
                btMax.setVisible(false);
                btRe.setVisible(true);
                width = getWidth();
                ipTitle.setSize(width,100);
                btExit.setLocation(width-30, 0);
                btRe.setLocation(width-60, 0);
                btMini.setLocation(width-90, 0);
            }
        });

        btRe = new JButton();
        btRe.setBounds(width-60, 0, 30, 30);
        btRe.setIcon(new ImageIcon("src//main//res//icons//re.png"));
        btRe.setRolloverIcon(new ImageIcon("src//main//res//icons//re_red.png"));
        btRe.setMargin(new Insets(0, 0, 0, 0));
        btRe.setBorder(null);
        btRe.setContentAreaFilled(false);
        btRe.setVisible(false);
        btRe.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                btRe.setVisible(false);
                btMax.setVisible(true);
                setSize(1200,800);
                width = getWidth();
                ipTitle.setSize(width,100);
                btExit.setLocation(width-30, 0);
                btMax.setLocation(width-60, 0);
                btMini.setLocation(width-90, 0);
            }
        });

        btMini = new JButton();
        btMini.setIcon(new ImageIcon("src//main//res//icons//mini.png"));
        btMini.setRolloverIcon(new ImageIcon("src//main//res//icons//mini_red.png"));
        btMini.setBounds(width-90, 0, 30, 30);
        btMini.setMargin(new Insets(0, 0, 0, 0));
        btMini.setBorder(null);
        btMini.setContentAreaFilled(false);
        btMini.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                setExtendedState(JFrame.ICONIFIED);
            }
        });

        add(ipTitle);
        ipTitle.add(btExit);
        ipTitle.add(btMax);
        ipTitle.add(btRe);
        ipTitle.add(btMini);
    }

    public static void on() {
        new MainFrame().setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
