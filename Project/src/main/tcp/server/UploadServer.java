package main.tcp.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadServer {
    public static void main(String[] args) {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(10135);
            System.out.println("server start");
            while(true){
                Socket socket = ss.accept();
                new UploadThread(socket);
            }
        } catch (IOException e) {
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

class UploadThread extends Thread{

    Socket socket;
    InputStream is;
    OutputStream os;
    String fileDir = "c:\\test\\";

    public UploadThread(Socket socket){
        this.socket = socket;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        start();
    }

    public void run(){
        try {
            DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
            int size = 8192;
            byte[] b = new byte[size];
            long len = 0;
            String filename = fileDir + dis.readUTF();
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
            len = dis.readLong();
            System.out.println("len="+len);
            int read = 0;
            while(true){
                if(dis != null){
                    read = dis.read(b);
                }
                if(read == -1){
                    break;
                }
            }
            dos.write(b, 0, read);
            System.out.println("file recieved");
            dis.close();
            dos.close();
            socket.shutdownInput();
            socket.shutdownOutput();
            socket.close();
        } catch (Exception e) {
            
        }
    }

}