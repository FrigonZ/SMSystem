package main.frigon;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class TestFrame extends JFrame{

    private static final long serialVersionUID = 1L;
    JButton btExit,btMini,btMax;
    int preX = 0;
    int preY = 0;
    
    public TestFrame(){

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
        btExit = new JButton("exit");
        btExit.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btMax = new JButton("max");
        btMax.setBounds(0, 0, 50, 50);
        btMax.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
                ipTitle.setSize(getWidth(),100);
            }
        });
        btMini = new JButton("mini");
        btMini.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                setExtendedState(JFrame.ICONIFIED);
            }
        });

        add(ipTitle);
        ipTitle.add(btMax);
        
    }

    public static void main(String[] args) {
        new TestFrame().setVisible(true);
    }
}