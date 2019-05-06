package main.tcp.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import main.mysql.MySQL;

public class NoticeThread extends Thread {

    Socket socket;
    InputStream is;
    OutputStream os;

    public NoticeThread(Socket socket){
        this.socket = socket;
        init();
        start();
    }

    public void run(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = br.readLine();
            System.out.println("recieved"+str);
            socket.shutdownInput();
            String[] info = str.split("&");
            new MySQL().insert(info[0], info[1]);
            String msg = "recieved";
            os.write(msg.getBytes());
            socket.shutdownOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(is != null)
                    is.close();
                if(os != null)
                    os.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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