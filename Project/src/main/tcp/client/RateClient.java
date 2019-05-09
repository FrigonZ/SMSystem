package main.tcp.client;

import java.io.*;
import java.net.Socket;


public class RateClient{
    
    private static Socket socket = null;
    private static InputStream is = null;
    private static OutputStream os = null;
    private static String ip = "127.0.0.1";
    String data;
    String str;

    public RateClient(String data){
        try {
            this.data = data;
            socket = new Socket(ip,10133);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            System.out.println("client start");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendMsg(){
        try {
            os.write(data.getBytes());
            socket.shutdownOutput();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            str = br.readLine();
            System.out.println(str);
            socket.shutdownInput();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
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
        //new RateClient("1#name&str&choice1&choice2");
        new RateClient("2#测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试#2018004").sendMsg();

    }
}