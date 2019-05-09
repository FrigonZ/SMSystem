package main.tcp.server;

import java.net.ServerSocket;
import java.net.Socket;

public class RateServer extends Thread{
    public void run() {
        final int port = 10133;

        ServerSocket ss = null;
        try {
            RateThread.setNew("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试&str&choice1&choice2");
            ss = new ServerSocket(port);
            System.out.println("Server start");
            while(true){
                Socket socket = ss.accept();
                System.out.println("socket connected");
                new RateThread(socket);
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