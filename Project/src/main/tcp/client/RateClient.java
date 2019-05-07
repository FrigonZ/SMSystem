package main.tcp.client;

import java.io.*;
import java.net.Socket;


public class RateClient extends Thread{
    
    private static Socket socket = null;
    private static InputStream is = null;
    private static OutputStream os = null;
    private static String ip = "127.0.0.1";
    String data;

    public RateClient(String data){
        try {
            this.data = data;
            socket = new Socket(ip,10133);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            System.out.println("client start");
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            os.write(data.getBytes());
            socket.shutdownOutput();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = br.readLine();
            System.out.println(str);
            socket.shutdownInput();
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

    public static void main(String[] args) {
        new RateClient("1#name&str&choice1&choice2");
    }
}