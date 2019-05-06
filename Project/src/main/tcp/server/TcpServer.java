package main.tcp.server;

import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer{
    public static void main(String[] args) {
        final int pLog = 10129;

        ServerSocket ssLog = null;
        try {
            ssLog = new ServerSocket(pLog);
            System.out.println("Server start");
            while(true){
                Socket socket = ssLog.accept();
                new LogThread(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                ssLog.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}