package main.frigon;

import java.awt.*;
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
        JLabel lbChange;
        JTextField tfUser;
        JPasswordField pfPass;
        ImagePanel ipBg = new ImagePanel(new ImageIcon("src//main//res//images//bg.png"));

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
        ImageIcon logo = new ImageIcon("src//main//res//icons//logo.png");
        setIconImage(logo.getImage());


        //右上角退出button
        btExit = new JButton();
        btExit.setIcon(new ImageIcon("src//main//res//icons//exit.png"));
        btExit.setRolloverIcon(new ImageIcon("src//main//res//icons//exit_red.png"));
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

        //最小化button
        btMini = new JButton();
        btMini.setIcon(new ImageIcon("src//main//res//icons//mini.png"));
        btMini.setRolloverIcon(new ImageIcon("src//main//res//icons//mini_red.png"));
        btMini.setBounds(340, 0, 30, 30);
        btMini.setMargin(new Insets(0, 0, 0, 0));
        btMini.setBorder(null);
        btMini.setContentAreaFilled(false);
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
        btLog = new JButton();
        btLog.setIcon(new ImageIcon("src//main//res//images//log.png"));
        btLog.setRolloverIcon(new ImageIcon("src//main//res//images//log_highlight.png"));
        btLog.setBounds(50, 240, 300, 35);
        btLog.setBorderPainted(false);
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
        lbChange = new JLabel("更改密码");
        lbChange.setBounds(295, 270, 75, 30);
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

        ipBg.setBounds(0, 0, 400, 155);
        ipBg.add(btExit);
        ipBg.add(btMini);

        add(btLog);
        add(tfUser);
        add(pfPass);
        add(lbChange);
        add(ipBg);
    }
    public static void main(String[] args) {
        new LogFrame().setVisible(true);
    }
}