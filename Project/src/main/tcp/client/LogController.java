package main.tcp.client;

import main.Main;
import javax.swing.JOptionPane;

public class LogController {

    public static int log(String username,String password){
        if(LogClient.checkPass(username, password)){
            Main.setAccount(username);
            if(username.equals("2018001"))
                Main.setAdmin(true);
            Main.begin();
            return 1;
        }
        else{
            JOptionPane.showMessageDialog(null, "You enter the wrong password", "Wrong", JOptionPane.INFORMATION_MESSAGE);
            return 0;
        }
    }
}