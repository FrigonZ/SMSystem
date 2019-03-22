package main.frigon;

public class User{

    private String account;
    private String name;
    private String password;
    private boolean power;//true == admin

    public User(){
        account = null;
        name = null;
        password = null;
        power = false;
    }

    public User(String account,String name,String password){
        this.account = account;
        this.name = name;
        this.password = password;
    }

    public boolean checkAdmin(){
        return power;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getAccount(){
        return account;
    }

    public String getName(){
        return name;
    }

    public boolean checkPass(String password){
        if(this.password.equals(password))
            return true;
        else
            return false;
    }
}