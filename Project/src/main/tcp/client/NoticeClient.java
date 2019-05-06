package main.tcp.client;

import java.io.*;
import java.net.Socket;

import javax.swing.JOptionPane;


public class NoticeClient extends Thread {

    InputStream is = null;
    OutputStream os = null;
    BufferedReader br = null;
    Socket socket = null;
    String user = null;
    String notice = null;

    public NoticeClient(String user,String notice){
        try {
            socket = new Socket("127.0.0.1",10131);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            this.user = user;
            this.notice = notice;
            System.out.println("client start");
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String data = user + "&" + notice;
            os.write(data.getBytes());
            socket.shutdownOutput();
            System.out.println("data sent");
            String msg = br.readLine();
            socket.shutdownInput();
            System.out.println(msg);
            if(msg != null){
                JOptionPane.showMessageDialog(null, "Notice Reached", "Notice", JOptionPane.INFORMATION_MESSAGE);
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

    public static void main(String[] args) {
        new NoticeClient("test", "notice test");
    }
}