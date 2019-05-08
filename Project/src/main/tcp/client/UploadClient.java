package main.tcp.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class UploadClient extends Thread{
    String fileName;
    
    public UploadClient(String file){
        fileName = file;
        start();
    }

    public void run(){
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 10135);
            File file = new File(fileName);
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(file.getName());
            dos.flush();
            dos.writeLong((long)file.length());
            dos.flush();
            int size = 8192;
            byte[] b = new byte[size];
            while(true){
                int read = 0;
                if(dis != null){
                    read = dis.read(b);
                }
                if(read == -1){
                    break;
                }
                dos.write(b, 0, read);
            }
            dos.flush();
            dos.close();
            dis.close();
            socket.close();
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UploadClient("c:\\test\\up\\test.txt");
    }
}