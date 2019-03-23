package main.ui;

import java.awt.*;
import javax.swing.*;

public class InfoPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    CardLayout c = new CardLayout();
    JPanel pnTop,pnBot;
    JButton btNew,btFirst,btPre,btNext,btLast;

    public InfoPanel(int x,int y){

        setSize(x,y);
        setLayout(null);

        pnTop = new JPanel(c);
        pnTop.setSize(getWidth(),getHeight()-50);
        pnTop.setLocation(0, 0);

        pnBot = new JPanel();
        pnBot.setSize(getWidth(),50);
        pnBot.setLocation(0, getHeight()-50);

        btFirst = new JButton("第一条");
        btFirst.setSize(60, 30);

        btPre = new JButton("上一条");
        btPre.setSize(60, 30);

        btNew = new JButton("新通知");
        btNew.setSize(60, 30);

        btNext = new JButton("下一条");
        btNext.setSize(60, 30);

        btLast = new JButton("最后一条");
        btLast.setSize(60, 30);

        pnBot.add(btFirst);
        pnBot.add(btPre);
        pnBot.add(btNew);
        pnBot.add(btNext);
        pnBot.add(btLast);

        add(pnTop);
        add(pnBot);
    }

    public void reFresh(){
        pnTop.setSize(getWidth(),getHeight()-50);
        pnTop.setLocation(0, 0);
        pnBot.setSize(getWidth(),50);
        pnBot.setLocation(0, getHeight()-50);
    }
}