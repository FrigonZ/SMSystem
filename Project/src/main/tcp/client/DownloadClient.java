package main.tcp.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

public class DownloadClient {

    Map<String,File> map;
    Socket s;

    public String getList(){
        
        try {
            //连接到指定地址指定端口服务
            s = new Socket("127.0.0.1",10134);
            //获取基于Socket的对象输入流(Map)
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            //读取一个对象
            map = (Map<String,File>)ois.readObject();
            //获取map集合中的键集
            Set<String> keys = map.keySet();
            //获取set对象的迭代器
            Iterator<String> it = keys.iterator();
            //迭代
            String data = "";
            while(it.hasNext()){
                //获取迭代器中的一个元素
                String key = it.next();
                //根据键获取值
                File value = map.get(key);
                data += value.getName()+"&";
            }
            data = data.substring(0, data.length()-1);
            return data;
            
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    public void Download(String no){
        try {
            //根据编号或许需要下载的文件的文件名
            String fname = map.get(no).getName();
            
            //发送指令到服务器
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            pw.println(no);
            pw.flush();
            
            //准备本地文件的输出流，存储来自socket的输入流中文件数据
            BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fname));
            byte[] b = new byte[1024];
            int len = 0;
            System.out.println("开始下载...");
            while((len = bis.read(b)) != -1){
                bos.write(b,0,len);
            }
            System.out.println("下载完成!");
            getFile(b, "c:\\test\\down", fname);
            JOptionPane.showMessageDialog(null, "Done", "Download", JOptionPane.INFORMATION_MESSAGE);
            s.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (Exception e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (Exception e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }
    }  
}