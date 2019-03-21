package main.frigon;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IconButton extends JButton{

    private static final long serialVersionUID = 1L;
    ImageIcon preIcon;
    ImageIcon nowIcon;
    ImageIcon aftIcon;

    public IconButton(ImageIcon pre,ImageIcon now){
        super();
        preIcon = pre;
        nowIcon = now;
        setIcon(preIcon);
        setRolloverIcon(nowIcon);
        setMargin(new Insets(0, 0, 0, 0));
        setBorderPainted(false);
        setBorder(null);
        setContentAreaFilled(false);
    }

    public IconButton(ImageIcon icon){
        super();
        preIcon = icon;
        setIcon(preIcon);
        setMargin(new Insets(0, 0, 0, 0));
        setBorderPainted(false);
        setBorder(null);
        setContentAreaFilled(false);
    }

    public IconButton(ImageIcon pre,ImageIcon now,ImageIcon aft){
        super();
    }
}