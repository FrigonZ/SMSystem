package main.tcp.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import main.mysql.MySQL;

public class Rate{
    //匿名投票类
    Random random;
    int[] key;
    String name;
    String str;
    Choice[] choice;
    Map<String ,String> map = new HashMap<String ,String>();
    Map<String ,String> uMap = new HashMap<String ,String>();

    public Rate(String msg){
        random = new Random();
        String[] info = msg.split("&");
        int n = info.length;
        name = info[0];
        str = info[1];
        choice = new Choice[n-2];
        for(int i = 0;i<n-2;i++){
            choice[i] = new Choice(info[i+2]);
        }
        int row = new MySQL().getRow("select * from user");
        key = new int[row];
        random = new Random();
        for(int i = 0;i<row;i++){
            int a = random.nextInt(900)+100;
            key[i] = a;
            map.put(String.valueOf(a),"0");
        }
        shuffle();
        System.out.println(map.toString());
        System.out.println("Rate setted");
    }

    public String getName(){
        return name;
    }

    public String getData(){
        String data = name+"#"+str;
        for(int i = 0;i < choice.length;i++){
            data += "#";
            data += choice[i].name;
        }
        return data;
    }

    public void shuffle(){
        for (int i = 0; i < key.length; i++) {
            int s = (int)(Math.random()*key.length);
            int temp = key[i];
            key[i] = key[s];
            key[s] = temp;
         }
    }

    public boolean checkUser(String account){
        if(uMap.containsKey(account)){
            System.out.println(uMap.toString());
            return false;
        }else{
            return true;
        }
    }

    public boolean checkKey(String key){
        if(map.get(key).equals("0")){
            return true;
        }else{
            return false;
        }
    }

    public int getKey(String account){
        uMap.put(account, "1");
        int n = key[0];
        if(key.length>1){
            int[] temp = new int[key.length-1];
            for(int i = 0;i<key.length-1;i++){
                temp[i] = key[i+1];
            }
            key = temp;
            shuffle();
            return n;
        }else{
            return n;
        }
    }

    public void useKey(int key,int id){
        map.put(String.valueOf(key), "1");
        choice[id].num++;
        System.out.println(choice[id].name+" "+choice[id].num);
        System.out.println(map.toString());
    }

    public static void main(String[] args) {
        String msg = "user&str&c1&c2&c3";
        Rate rate = new Rate(msg);
        int[] a = new int[3];
        for(int i = 0;i<3;i++){
            a[i] = rate.getKey("201800"+i);
            System.out.println(a[i]);
        }
        rate.useKey(a[0],0);
        rate.useKey(a[2],2);
        if(rate.checkKey(String.valueOf(a[0]))){
            rate.useKey(a[0], 1);
        }else{
            System.out.println("key used");
        }
        if(rate.checkUser("2018001")){
            System.out.println(rate.getKey("2018001"));
        }else{
            System.out.println("account used");
        }
    }
}

class Choice{

    String name;
    int num;

    public Choice(String name){
        this.name = name;
        num = 0;
    }
}
