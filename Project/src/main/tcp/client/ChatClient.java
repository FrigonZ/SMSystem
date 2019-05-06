package main.tcp.client;

import java.io.*;
import java.net.Socket;

public class ChatClient extends Thread{

    private static InputStream is = null;
    private static OutputStream os = null;
    private static BufferedReader br = null;
    private static PrintWriter pw = null;
    private static Socket socket = null;
    private static String user = null;
    int con = 1;

    public ChatClient(){
        try {
            socket = new Socket("127.0.0.1",10130);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            System.out.println("client start");
            start();
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

    public void run(){
        br = new BufferedReader(new InputStreamReader(is));
        pw = new PrintWriter(os);
        System.out.println("br start");
        try {
            while(true){
                String line = br.readLine();
                System.out.println("data recieved");
                System.out.println(line);
                if(con == 0)
                    break;
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

    public void close(){
        con = 0;
    }

    public void setUser(){
        user = null;
    }

    public void setUser(String username){
        user = username;
        System.out.println("user setted");
    }
}