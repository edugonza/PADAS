package org.processmining.database.redologs.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.plaf.InternalFrameUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.jfree.text.G2TextMeasurer;
 

public class CustomInternalFrame extends JInternalFrame implements InternalFrameListener {
 
    private static Map desktopHints;
    private InternalFrameUI ui = this.getUI();
	private BasicInternalFrameUI bifui = (BasicInternalFrameUI) ui;

	public CustomInternalFrame(String title) {
        super(title, true, true, true, true);
        addInternalFrameListener(this);
    }
	
    public void setIcon(Icon anIcon){
        setFrameIcon(anIcon);
    }
     
    @Override
    protected void paintComponent(Graphics g) {
    	/* Trick to enable antialiasing fonts on title bar */
    	if (desktopHints == null) { 
    	    Toolkit tk = Toolkit.getDefaultToolkit(); 
    	    desktopHints = (Map) (tk.getDesktopProperty("awt.font.desktophints")); 
    	}
    	if (desktopHints != null) {
    		Graphics2D g2d = (Graphics2D) g;
    	    g2d.addRenderingHints(desktopHints); 
    	}
    	/**/
        g.setColor(new Color(100, 0, 4, 150));
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
    }

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		bifui.getNorthPane().setForeground(Color.WHITE);
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		bifui.getNorthPane().setForeground(Color.GRAY);
	}
 
}