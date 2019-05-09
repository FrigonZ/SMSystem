package main;

import java.util.HashMap;
import java.util.Map;

import main.tcp.client.ChatClient;
import main.ui.ChatFrame;
import main.ui.LogFrame;
import main.ui.MainFrame;

public class Main{

    public static ChatClient cc;
    public static String user;
    public static String account;
    public static Map<String ,ChatFrame> map = new HashMap<String ,ChatFrame>();
    public static void main(String[] args) {
        LogFrame.on();
    }

    public static void begin(){
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        cc = new ChatClient();
        cc.setUser(user);
        cc.setText(mainFrame.getTa());
        cc.sendMsg();
    }

    public static void setUser(String user){
        Main.user = user;
        System.out.println("------------------user set :"+Main.user+"----------------");
    }

    public static void setAccount(String account){
        Main.account = account;
        System.out.println("------------------account set :"+Main.account+"----------------");
    }
}