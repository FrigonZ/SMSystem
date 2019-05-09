package main.tcp.server;

import java.io.*;
import java.net.Socket;

import main.mysql.MySQL;

public class LogThread extends Thread{
    Socket socket;
    InputStream is;
    OutputStream os;

    public LogThread(Socket socket){
        this.socket = socket;
        init();
        start();
    }

    public void run(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = br.readLine();
            System.out.println("recieve:" + str);
            socket.shutdownInput();
            String[] info = str.split("&");
            String reply = "1";
            MySQL mySQL = new MySQL();
            if(mySQL.log(info[0], info[1])){
                reply = new MySQL().checkName(info[0]);
            }
            os.write(reply.getBytes());
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