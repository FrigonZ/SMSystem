package main.ui;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private ImageIcon imageIcon;
    private static final long serialVersionUID = 1L;

    public ImagePanel(ImageIcon imageIcon) {
        super();
        this.imageIcon = imageIcon;
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        imageIcon.paintIcon(this, g, 0, 0);
    }
}