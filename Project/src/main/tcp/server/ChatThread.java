package main.tcp.server;

import java.io.*;
import java.net.Socket;


public class ChatThread extends Thread{

    Socket socket;
    InputStream is;
    OutputStream os;
    PrintWriter pw;
    private String username;

    public ChatThread(Socket socket){
        this.socket = socket;
        init();
        System.out.println("chatthread start");
        start();
    }

    public void run(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            ChatController.addThread(this);
            while(true){
                String str = br.readLine();
                System.out.println(str);
                if(!str.equals("&&&")){
                    System.out.println("recieve:"+str);
                    String[] info = str.split("&");
                    if(info.length == 4){
                        ChatController.addThread(this, info[0]);
                    }
                    if(info.length == 2){
                        System.out.println("msg ready");
                        ChatController.sendMsg(info[0], info[1]);
                    }
                    if(info.length == 3){
                        System.out.println("msg ready");
                        ChatController.sendMsg(info[0], info[1],info[2]);
                    }
                }else{
                    break;
                }
            }
            socket.shutdownInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg){
        try {
            pw = new PrintWriter(os);
            pw.println(msg);
            System.out.println("msg writed");
            pw.flush();
            System.out.println("os flushed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUser(){
        return username;
    }

    private void init(){
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}