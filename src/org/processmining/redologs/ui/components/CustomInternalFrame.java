package org.processmining.redologs.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JInternalFrame;
 

public class CustomInternalFrame extends JInternalFrame{
 
    public CustomInternalFrame(String title) {
        super(title, true, true, true, true);
    }
 
    public void setIcon(Icon anIcon){
        setFrameIcon(anIcon);
    }
     
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(100, 0, 4, 85));
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
    }
 
}