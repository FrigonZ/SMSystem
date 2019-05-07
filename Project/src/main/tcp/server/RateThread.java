package main.tcp.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class RateThread extends Thread{

    Socket socket;
    InputStream is;
    OutputStream os;
    private static Map<String ,Rate> rateMap = new HashMap<String ,Rate>();
    
    public RateThread(Socket socket){
        this.socket = socket;
        init();
        start();
    }

    public void run(){
        try{
            System.out.println("thread start");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = br.readLine();
            System.out.println("recieved"+str);
            socket.shutdownInput();
            String[] info = str.split("#");
            System.out.println(info[0]);
            switch(info[0]){
                case "1":
                    StartRate(info[1]);
                    break;
                case "2":
                    vote(info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]));
                    break;
                case "3":
                    getKey(info[1], info[2]);
                    break;
                case "4":
                    getData();
                    break;
                default:
                    String reply = "error";
                    os.write(reply.getBytes());
            }
            socket.shutdownOutput();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void StartRate(String msg)throws Exception{
        Rate rate = new Rate(msg);
        rateMap.put(rate.name, rate);
        System.out.println("new Rate set");
        String reply = "1";
        os.write(reply.getBytes());
    }

    private void vote(String name,int key,int choice)throws Exception{
        Rate rate = rateMap.get(name);
        String reply = "2";
        if(rate.checkKey(String.valueOf(key))){
            rate.useKey(key, choice);
        }else{
            reply = "0";
        }
        os.write(reply.getBytes());
    }

    private void getKey(String name,String account)throws Exception{
        Rate rate = rateMap.get(name);
        String reply = null;
        if(rate.checkUser(account)){
            reply = String.valueOf(rate.getKey(account));
        }else{
            reply = "0";
        }
        os.write(reply.getBytes());
    }

    private void getData()throws Exception{
        String data = "";
        for(String key:rateMap.keySet()){
            Rate rate = rateMap.get(key);
            data = data+rate.toString()+"&";
        }
        data = data.substring(0, data.length()-1);
        os.write(data.getBytes());
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