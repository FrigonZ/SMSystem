package main.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import main.tcp.client.DownloadClient;

public class FileFrame extends JFrame{
    private static final long serialVersionUID = 1L;
    JScrollPane scrollPane;


    public FileFrame(){
        setSize(600,800);
        setLocationRelativeTo(null);
        setResizable(false);
        scrollPane = new JScrollPane();
        DownloadClient dc = new DownloadClient();
        String data = dc.getList();
        String[] str = data.split("&");
        int n = str.length;
        JButton[] bt = new JButton[n];
        for(int i = 0;i<n;i++){
            bt[i] = new JButton(str[i]);
            bt[i].setSize(getWidth(), 50);
            bt[i].setLocation(0, 50*i);
            String no = String.valueOf(i+1);
            bt[i].addActionListener(new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e) {
                    dc.Download(no);
                }
            });
            scrollPane.add(bt[i]);
        }
        add(scrollPane);
    }

    public static void main(String[] args) {
        new FileFrame().setVisible(true);
    }
}