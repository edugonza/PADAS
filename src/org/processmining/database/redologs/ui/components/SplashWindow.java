package org.processmining.database.redologs.ui.components;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class SplashWindow extends JWindow {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6433690018936176703L;

	private Thread splashThread;
	
	private Image bi;
	  
	ImageIcon ii;  
	
    public SplashWindow(URL filename, Frame f, int waitTime)
    {
        //super(f);
        
        bi=Toolkit.getDefaultToolkit().getImage(filename);
        ii=new ImageIcon(bi);
        
        setSize(ii.getIconWidth(),ii.getIconHeight());  

//        JLabel l = new JLabel(new ImageIcon(filename));
//        getContentPane().add(l, BorderLayout.CENTER);
//        pack();
        //Dimension screenSize =
          //Toolkit.getDefaultToolkit().getScreenSize();
        //Dimension labelSize = l.getPreferredSize();
        //setLocation(screenSize.width/2 - (labelSize.width/2),
        //            screenSize.height/2 - (labelSize.height/2));
        
        setLocationRelativeTo(null);
        
        addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent e)
                {
                    setVisible(false);
                    dispose();
                }
            });
        final int pause = waitTime;
        final Runnable closerRunner = new Runnable()
            {
                public void run()
                {
                    setVisible(false);
                    dispose();
                }
            };
        Runnable waitRunner = new Runnable()
            {
                public void run()
                {
                    try
                        {
                            Thread.sleep(pause);
                            SwingUtilities.invokeAndWait(closerRunner);
                        }
                    catch(Exception e)
                        {
                            e.printStackTrace();
                            // can catch InvocationTargetException
                            // can catch InterruptedException
                        }
                }
            };
        //setVisible(true);
            
        setBackground(new Color(0, 255, 0, 0));
        setVisible(true);
        splashThread = new Thread(waitRunner, "SplashThread");
        splashThread.start();
    }
    
    public void paint(Graphics g)  
    {  
    	g.drawImage(bi,0,0,this);  
    }  
    
}