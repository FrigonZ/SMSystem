package main.tcp.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;



public class TcpClient{

    private static Socket socket = null;
    private static InputStream is = null;
    private static OutputStream os = null;
    private static String ip = "127.0.0.1";
    String user = null;


    public void setStudent(){
        user = null;
    }

    public void setStudent(String account){
        user = account;
    }

    public static boolean checkPass(String username,String password){

        int port = 10129;
        String data = username + "&" + password;

        try {
            socket = new Socket(ip, port);
            os = socket.getOutputStream();
            is = socket.getInputStream();
            byte[] b = data.getBytes();
            os.write(b);
            socket.shutdownOutput();
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));
            String str = br.readLine();
            System.out.println(str);
            socket.shutdownInput();
            if(str.equals("1"))
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            try {
                if(os != null)
                    os.close();
                if(is != null)
                    is.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}