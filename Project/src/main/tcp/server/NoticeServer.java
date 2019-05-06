package main.tcp.server;

import java.net.ServerSocket;
import java.net.Socket;

public class NoticeServer{

    public static void main(String[] args) {
        final int port = 10131;
        ServerSocket ss = null;

        try {
            ss = new ServerSocket(port);
            System.out.println("Server start");
            while(true){
                Socket socket = ss.accept();
                new NoticeThread(socket);
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