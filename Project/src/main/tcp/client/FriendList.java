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

    public FriendList(String account){
        this.account = account;
        try {
            socket = new Socket("127.0.0.1",10132);
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
            System.out.println("account sent"+account);
            socket.shutdownOutput();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while(true){
                String line = br.readLine();
                if(line.equals("quit")){
                    break;
                }else{
                    System.out.println(line);
                }
            }
            socket.shutdownInput();
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