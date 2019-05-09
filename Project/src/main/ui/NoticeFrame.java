package main.ui;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import main.Main;
import main.tcp.client.NoticeClient;

public class NoticeFrame extends JFrame{

    private static final long serialVersionUID = 1L;

    public NoticeFrame() {
        setSize(600,400);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        TextArea ta = new TextArea("输入公告内容");
        ta.setBounds(20, 0, 560, 320);

        JButton bt = new JButton("ok");
        bt.setBounds(20, 330, 560, 30);
        bt.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                String notice = ta.getText().toString();
                new NoticeClient(Main.user, notice);
            }
        });

        add(ta);
        add(bt);
    }

    public static void main(String[] args) {
        new NoticeFrame().setVisible(true);
    }
}