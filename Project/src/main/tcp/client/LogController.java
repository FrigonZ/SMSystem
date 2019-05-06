package main.tcp.client;

import main.ui.MainFrame;
import javax.swing.JOptionPane;

public class LogController {
    public static int log(String username,String password){
        if(TcpClient.checkPass(username, password)){
            MainFrame.on();
            
            return 1;
        }
        else{
            JOptionPane.showMessageDialog(null, "You enter the wrong password", "Wrong", JOptionPane.INFORMATION_MESSAGE);
            return 0;
        }
    }
}