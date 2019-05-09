package main.tcp.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class UploadClient extends Thread{
    String fileName;
    File file;
    
    public UploadClient(File file){
        this.file = file;
        fileName = file.getName();
        start();
    }

    public void run(){
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 10135);
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
            JOptionPane.showMessageDialog(null, "Done", "Upload", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}