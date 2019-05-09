package main.ui;

import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import main.tcp.client.RateClient;

public class VoteFrame extends JFrame{

    private static final long serialVersionUID = 1L;

    public VoteFrame() {
        setSize(600,400);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        TextField tfTitle = new TextField("主题");
        tfTitle.setBounds(10, 10, getWidth()-30, 30);

        TextArea ta = new TextArea("输入投票内容");
        ta.setBounds(10, 50, getWidth()-30, 230);

        TextField tfChoice = new TextField("请用&连接选项");
        tfChoice.setBounds(10, 290, getWidth()-30, 30);

        JButton bt = new JButton("ok");
        bt.setBounds(10, 330, getWidth()-30, 30);
        bt.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = "1#"+tfTitle.getText().toString()+"&"+ta.getText().toString()+"&"+tfChoice.getText().toString();
                new RateClient(msg).sendMsg();
            }
        });

        add(tfTitle);
        add(tfChoice);
        add(ta);
        add(bt);
    }

    public static void main(String[] args) {
        new VoteFrame().setVisible(true);
    }
}