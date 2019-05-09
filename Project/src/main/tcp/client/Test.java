package main.tcp.client;

public class Test{
    public static void main(String[] args) {
        ChatClient cc = new ChatClient();
        cc.setUser("username");
        cc.sendMsg();
        cc.sendMsg("test1");
        cc.sendMsg("test2");
        cc.sendMsg("admin", "msg");
    }
}
