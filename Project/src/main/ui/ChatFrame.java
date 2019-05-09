package main.ui;
/*
单人聊天界面
*/

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.Main;

public class ChatFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    TextArea ta;
    JTextField tf;
    JButton btSend;
    String name;

    public ChatFrame(String name){
        this.name = name;
        setSize(1200,800);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle(name);
        
        ta = new TextArea();
        ta.setBounds(0, 50, 1200, 500);

        tf = new JTextField();
        tf.setBounds(0, 570, 1200, 100);

        btSend = new JButton("send");
        btSend.setBounds(20, 690, 1160, 60);
        btSend.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tf.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "内容不可为空", "msg", JOptionPane.WARNING_MESSAGE);
                }else{
                    String msg = tf.getText().toString();
                    ta.append(msg+"\n");
                    sendMsg(msg);
                }
            }
        });

        add(ta);
        add(tf);
        add(btSend);
    }

    public void sendMsg(String msg){
        Main.cc.sendMsg(name, msg);
    }

    public TextArea getTa(){
        return ta;
    }

    public static void main(String[] args) {
        new ChatFrame("name").setVisible(true);
    }
}