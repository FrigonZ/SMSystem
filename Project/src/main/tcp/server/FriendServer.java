package main.tcp.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import main.mysql.MySQL;

public class FriendServer extends Thread{
    public void run() {
        final int port = 10132;

        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);
            System.out.println("Server start");
            while(true){
                Socket socket = ss.accept();
                System.out.println("socket connected");
                new FriendThread(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                ss.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class FriendThread extends Thread{

    Socket socket;
    InputStream is;
    OutputStream os;
    String account;

    public FriendThread(Socket socket){
        this.socket = socket;
        init();
        start();
    }

    public void run(){
        System.out.println("thread start");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            PrintWriter pw = new PrintWriter(os);
            System.out.println("1");
            String msg = br.readLine();
            System.out.println("recieve " + msg);
            socket.shutdownInput();
            int id = 0;
            id = new MySQL().getId(msg);
            int row = new MySQL().getRow("select * from user");
            for(int i = 1;i <= row;i++){
                if(i != id){
                    pw.println(new MySQL().getData(i));
                    System.out.println("sending"+i);
                    pw.flush();
                }
            }
            pw.println("quit");
            socket.shutdownOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
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