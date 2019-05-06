package main.ui;

/*
登录界面
*/

import main.tcp.client.LogController;

import java.awt.event.*;
import javax.swing.*;

public class LogFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    int preX = 0;
    int preY = 0;

    public LogFrame() {

        JButton btLog;
        JButton btExit;
        JButton btMini;
        JTextField tfUser;
        JPasswordField pfPass;
        ImagePanel ipBg = new ImagePanel(new ImageIcon("src//main//res//images//bg.png"));

        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);//居中
        
        setUndecorated(true);//去除菜单栏
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);//锁定大小
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        //监听鼠标，实现窗体拖拽
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

        //设置logo
        ImageIcon logo = new ImageIcon("src//main//res//icons//logo.png");
        setIconImage(logo.getImage());


        //右上角退出button
        btExit = new IconButton(new ImageIcon("src//main//res//icons//exit.png"), new ImageIcon("src//main//res//icons//exit_red.png"));
        btExit.setBounds(370, 0, 30, 30);
        btExit.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //最小化button
        btMini = new IconButton(new ImageIcon("src//main//res//icons//mini.png"), new ImageIcon("src//main//res//icons//mini_red.png"));
        btMini.setBounds(340, 0, 30, 30);
        btMini.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                setExtendedState(JFrame.ICONIFIED);
            }
        });

        //用户名栏
        tfUser = new JTextField("用户名");
        tfUser.setBounds(50, 160, 300, 35);

        //密码栏
        pfPass = new JPasswordField();
        pfPass.setBounds(50, 200, 300, 35);

        //登录按钮
        btLog = new IconButton(new ImageIcon("src//main//res//images//log.png"), new ImageIcon("src//main//res//images//log_highlight.png"));
        btLog.setBounds(50, 240, 300, 35);
        getRootPane().setDefaultButton(btLog);
        btLog.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                //先检验用户名，密码是否为空
                if(tfUser.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "请输入学号", "Log", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    if(pfPass.getPassword().length<1){
                        JOptionPane.showMessageDialog(null, "请输入密码", "Log", JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        //登录验证，密码修改由LogController控制
                        String username = tfUser.getText().toString();
                        String password = new String(pfPass.getPassword());
                        int n = LogController.log(username,password);
                        if(n == 1)
                            dispose();
                        pfPass.setText("");
                        //输入密码后清空密码栏
                    }
                }
            }
        });


        ipBg.setBounds(0, 0, 400, 155);
        ipBg.add(btExit);
        ipBg.add(btMini);

        add(btLog);
        add(tfUser);
        add(pfPass);
        add(ipBg);
    }

    public static void on(){
        //显示登陆界面
        new LogFrame().setVisible(true);
    }
}