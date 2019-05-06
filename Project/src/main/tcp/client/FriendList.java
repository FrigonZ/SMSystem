package main.tcp.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class FriendList extends Thread{

    String account;
    Socket socket;
    InputStream is;
    OutputStream os;
    BufferedReader br;

    public FriendList(String account){
        this.account = account;
        try {
            socket = new Socket("127.0.0.1",10129);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            os.write(account.getBytes());
            System.out.println("account sent");
            br = new BufferedReader(new InputStreamReader(is));
            while(true){
                String line = br.readLine();
                if(line != "quit"){
                    System.out.println(line);
                }else{
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
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
        new FriendList("abc");
    }
}