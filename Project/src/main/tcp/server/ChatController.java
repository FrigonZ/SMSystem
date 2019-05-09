package main.tcp.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatController {
    
    private static ArrayList<ChatThread> ctList=new ArrayList<ChatThread>();
    private static Map<String,ChatThread> map = new HashMap<String,ChatThread>();

    private ChatController(){}

    public static void addThread(ChatThread chatThread)throws IOException{
        ctList.add(chatThread);
        System.out.println("thread added");
    }

    public static void addThread(ChatThread thread,String user)throws IOException{
        map.put(user, thread);
        System.out.println("thread added");
    }

    public static void sendMsg(String username){
        String data = username;
        for(int i = 0;i<ctList.size();i++){
            ChatThread ct = ctList.get(i);
            ct.sendMsg(data);
        }
    }

    public static void sendMsg(String user,String msg)throws IOException{
        String data = user + "&" + msg;
        for(int i = 0;i<ctList.size();i++){
            ChatThread ct = ctList.get(i);
            ct.sendMsg(data);
        }
    }

    public static void sendMsg(String user,String target,String msg)throws IOException{
        ChatThread thread = map.get(target);
        String data = user + "&" + msg + "&" + "p";
        thread.sendMsg(data);
    }
}