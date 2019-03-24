package main.ui;

//测试界面

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class TestFrame extends JFrame{

    private static final long serialVersionUID = 1L;
    String[] name = { "1", "2", "3", "4", "5" };
    
    public TestFrame(){

        setSize(1200, 800);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        CardLayout c = new CardLayout();
        JPanel p1 = new JPanel(c);
        JPanel p2 = new JPanel();
        for (int i = 0; i < name.length; i++) {
			p1.add(name[i], new Button(name[i]));
		}
 
		// 控制显示上一张的按钮
		Button previous = new Button("上一张");
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.previous(p1);
			}
		});
		// 控制显示下一张的按钮
		Button next = new Button("下一张");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.next(p1);
			}
		});
		// 控制显示第一张的按钮
		Button first = new Button("第一张");
		first.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.first(p1);
			}
		});
		// 控制显示最后一张的按钮
		Button last = new Button("最后一张");
		last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.last(p1);
			}
		});
		// 根据card名显示的按钮
		Button third = new Button("第三张");
		third.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.show(p1, "3");
			}
		});
		p2.add(previous);
		p2.add(next);
		p2.add(first);
		p2.add(last);
        p2.add(third);
        add(p1);// 默认添加到中间
		add(p2, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new TestFrame().setVisible(true);
    }
}