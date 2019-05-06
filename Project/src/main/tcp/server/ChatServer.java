package main.tcp.server;

import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer{
    public static void main(String[] args) {
        final int port = 10130;

        ServerSocket ssChat = null;
        try {
            ssChat = new ServerSocket(port);
            System.out.println("Server start");
            while(true){
                Socket socket = ssChat.accept();
                System.out.println("socket connected");
                new ChatThread(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                ssChat.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}