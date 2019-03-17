package main.frigon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LogFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public LogFrame() {
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        ImageIcon logo = new ImageIcon("src//main//res//icons//logo.png");
        setIconImage(logo.getImage());

        //背景label
        ImageIcon icBg = new ImageIcon("src//main//res//images//bg1.png");//bg。png为备用原版背景
        JLabel lbBg = new JLabel(icBg);
        lbBg.setBounds(0, 0, 400, 155);

        //用户名栏
        JTextField tfUser = new JTextField("Username");
        tfUser.setBounds(50, 160, 300, 35);

        //密码栏
        JPasswordField pfPass = new JPasswordField();
        pfPass.setBounds(50, 200, 300, 35);

        //登录按钮
        JButton btLog = new JButton(new ImageIcon("src//main//res//images//log.png"));
        btLog.setBounds(50, 240, 300, 35);
        btLog.setBorderPainted(false);
        btLog.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                //先检验用户名，密码是否为空
                if(tfUser.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter your username", "Log", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    if(pfPass.getPassword().length<1){
                        JOptionPane.showMessageDialog(null, "Please enter your password", "Log", JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        //登录验证，密码修改由LogController控制
                        int n = LogController.log(tfUser.getText().toString(),pfPass.getPassword().toString());
                        if(n == 1)
                            dispose();
                        pfPass.setText("");
                        //输入密码后清空密码栏
                    }
                }
            }
        });

        //忘记，更换密码入口
        JLabel lbChange = new JLabel("Change password");
        lbChange.setBounds(240, 270, 125, 30);
        lbChange.setForeground(Color.BLUE);
        lbChange.addMouseListener(new MouseListener(){
        //字体在被点击时变色，交互更加突出
            @Override
            public void mouseReleased(MouseEvent e) {
                lbChange.setForeground(Color.BLUE);
            }
        
            @Override
            public void mousePressed(MouseEvent e) {
                lbChange.setForeground(Color.BLACK);
            }
        
            @Override
            public void mouseExited(MouseEvent e) {

            }
        
            @Override
            public void mouseEntered(MouseEvent e) {

            }
        
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });

        //右上角退出button
        ImageIcon icExit = new ImageIcon("src//main//res//icons//exit.png");
        JButton btExit = new JButton(icExit);
        btExit.setBounds(370, 0, 30, 30);
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

        add(btLog);
        add(tfUser);
        add(pfPass);
        add(lbChange);
        add(lbBg);
        add(btExit);
    }
    public static void main(String[] args) {
        new LogFrame().setVisible(true);
    }
}