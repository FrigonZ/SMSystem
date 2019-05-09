package main.ui;

import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import main.Main;
import main.tcp.client.RateClient;

public class RateFrame extends JFrame{
    
    private static final long serialVersionUID = 1L;
    Rate rate;
    TextField tf,tfC;
    JButton bt,btC;
    JLabel lb;
    TextArea ta;



    public RateFrame(Rate rate){
        this.rate = rate;
        setSize(600,800);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        int n = rate.choice.length;

        String text = rate.name+"\n\t"+rate.str+"\n";

        for(int i = 0;i< n;i++){
            text = text+"\n\t"+rate.choice[i];
        }

        text = text+"\n\n"+"\t请获取密钥后在底部输入框输入选项编号，请妥善保管密钥";

        ta = new TextArea(text);
        ta.setSize(getWidth(),400);
        ta.setLocation(0, 0);
        
        tf = new TextField();
        tf.setBounds(10, 450, getWidth()-30, 30);


        bt = new JButton("获取密钥");
        bt.setSize(getWidth()-30, 30);
        bt.setLocation(10, 410);
        bt.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = "3#"+rate.name+"#"+Main.account;
                String key = new RateClient(msg).sendMsg();
                if(key.equals("0")){
                    key = "已获取过密钥";
                }
                tf.setText(key);
            }
        });

        tfC = new TextField("投票编号");
        tfC.setBounds(10, 500, getWidth()-30, 30);

        btC = new JButton("投票");
        btC.setBounds(10, 550, getWidth()-30, 30);
        btC.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = tf.getText().toString();
                int c = Integer.parseInt(tfC.getText().toString())-1;
                String msg = "2#"+rate.name+"#"+key+"#"+String.valueOf(c);
                String reply = new RateClient(msg).sendMsg();
                if(reply.equals("0")){
                    tfC.setText("投票失败");
                }else{
                    tfC.setText("投票成功");
                }
            }
        });

        add(ta);
        add(bt);
        add(tf);
        add(tfC);
        add(btC);
    }
}