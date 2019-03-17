package main.frigon;

import javax.swing.JOptionPane;

public class LogController {
    public static int log(String username,String password){
        if(checkPass(username, password)){
            MainFrame.on();
            return 1;
        }
        else{
            JOptionPane.showMessageDialog(null, "You enter the wrong password", "Wrong", JOptionPane.INFORMATION_MESSAGE);
            return 0;
        }
    }

    protected static boolean checkPass(String username,String password){
        return true;
    }  
}