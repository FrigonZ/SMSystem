package main.tcp.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class DownloadServer {
    public static void main(String[] args) {
        File dir = new File("c:\\test");
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(10134);
            System.out.println("server start");
            while(true){
                Socket socket = ss.accept();
                new DownloadThread(socket,dir);
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

class DownloadThread extends Thread{

    private Socket socket;
    private File dir;

    public DownloadThread(Socket socket,File dir){
        this.socket = socket;
        this.dir = dir;
        start();
    }

    public void run(){
        BufferedInputStream bis = null;
        try {
            File[] files = dir.listFiles();
            Map<String,File> map = new HashMap<>();
            for(int i = 0;i<files.length;i++){
                map.put(String.valueOf(i+1), files[i]);
            }
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			//发送对象到客户端Socket中
			oos.writeObject(map);
			//3.接收客户端的指令(需要下载的文件编号)
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String no = br.readLine();
			//4.根据文件编号找到指定文件并获取该文件的输入流
			File target = map.get(no);
			bis = new BufferedInputStream(new FileInputStream(target));
			//5.获取客户端Socket的输出流将读取的文件内容写出去
			//获取基于socket的输出流，向客户端发送文件数据
			BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
			byte[] b = new byte[1024];
			int len = 0;
			while((len = bis.read(b)) != -1){
				bos.write(b, 0, len);
			}
			bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
			try {
				if(socket != null){
					socket.close();
				}
				if(bis != null){
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
}