package main.tcp.client;

import java.awt.TextArea;
import java.io.*;
import java.net.Socket;

import main.Main;

public class ChatClient extends Thread{

    private static InputStream is = null;
    private static OutputStream os = null;
    private static BufferedReader br = null;
    private static PrintWriter pw = null;
    private static Socket socket = null;
    private static String user = null;
    private static TextArea ta = null;

    public ChatClient(){
        try {
            socket = new Socket("127.0.0.1",10130);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            br = new BufferedReader(new InputStreamReader(is));
            pw = new PrintWriter(os);
            System.out.println("client start");
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(){
        System.out.println("send ready");
        
        String data = user+"&2&3&4";
        try {
            pw.println(data);
            pw.flush();
            System.out.println("sending"+data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg){
        System.out.println("send ready");
        String data = user + "&" + msg;
        try {
            pw.println(data);
            pw.flush();
            System.out.println("sending"+data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String target,String msg){
        System.out.println("send ready");
        String data = user+"&"+target+"&"+msg;
        try {
            pw.println(data);
            pw.flush();
            System.out.println("sending"+data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        System.out.println("br start");
        try {
            while(true){
                String line = br.readLine();
                if(line != "&&&"){
                    System.out.println("data recieved");
                    System.out.println(line);
                    String[] str = line.split("&");
                    if(str.length == 2){
                        printMsg(line);
                    }
                    if(str.length == 3){
                        Main.map.get(str[0]).setVisible(true);
                        printP(line, Main.map.get(str[0]).getTa());
                    }
                }else{
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(socket != null){
                try {
                    socket.close();
                    System.out.println("socket closed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void printMsg(String line){
        String[] str = line.split("&");
        String msg = str[0] + ":\n\t" +str[1]+"\n";
        ta.append(msg);
    }

    private void printP(String line,TextArea textArea){
        String[] str = line.split("&");
        String msg = str[0] + ":\n\t" +str[1]+"\n";
        textArea.append(msg);
    }

    public void setUser(){
        user = null;
    }

    public void setUser(String username){
        user = username;
        System.out.println("user setted");
    }

    public String getUser(){
        return user;
    }

    public void setText(TextArea textArea){
        ta = textArea;
    }
}