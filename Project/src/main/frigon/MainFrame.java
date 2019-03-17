package main.frigon;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame{

    private static final long serialVersionUID = 1L;

    public MainFrame() {
        setSize(1200, 800);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        ImageIcon logo = new ImageIcon("src//main//res//icons//logo.png");
        setIconImage(logo.getImage());

        JButton btExit = new JButton("EXIT");
        btExit.setBounds(1150, 20, 30, 30);
        btExit.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(btExit);
    }

    public static void on() {
        new MainFrame().setVisible(true);
    }
}
