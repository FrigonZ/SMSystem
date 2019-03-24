package main.ui;

import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IconButton extends JButton{

    /*
    自定义类iconbutton
    实现按钮在鼠标经过时的图标交互
    */

    private static final long serialVersionUID = 1L;
    ImageIcon preIcon;
    ImageIcon nowIcon;
    ImageIcon aftIcon;

    public IconButton(ImageIcon pre,ImageIcon now){
        //pre为默认icon，now为光标经过时的icon
        super();
        preIcon = pre;
        nowIcon = now;
        setIcon(preIcon);
        setRolloverIcon(nowIcon);
        //去除button原有组件
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