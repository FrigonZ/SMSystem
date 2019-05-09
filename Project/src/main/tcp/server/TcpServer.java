package main.tcp.server;

public class TcpServer{
    public static void main(String[] args) {
        new LogServer().start();
        new ChatServer().start();
        new DownloadServer().start();
        new UploadServer().start();
        new NoticeServer().start();
        new RateServer().start();
    }
}