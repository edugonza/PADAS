package org.processmining.redologs.ui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JDialog;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window.Type;

import javax.swing.JTextPane;

import org.processmining.redologs.common.Constants;

import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JPanel;

public class AboutDialog extends JWindow {

	/**
	 * Create the dialog.
	 */
	public AboutDialog() {
		setAlwaysOnTop(true);
//		setUndecorated(true);
//		setResizable(false);
//		setModal(true);
		
		MouseAdapter closeOnMouseRelease = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				AboutDialog.this.setVisible(false);
				AboutDialog.this.dispose();
			}
		};
		setBackground(new Color(0, 0, 0, 0));
		
		addMouseListener(closeOnMouseRelease);
		setBounds(100, 100, 358, 581);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel lblByRrzeiconsown = new JLabel("");
		lblByRrzeiconsown.addMouseListener(closeOnMouseRelease);
		lblByRrzeiconsown.setToolTipText("<html>By RRZEicons (Own work) <br/>\n[CC-BY-SA-3.0]<br/>\nvia Wikimedia Commons</html>");
		lblByRrzeiconsown.setIcon(new ImageIcon(AboutDialog.class.getResource("/org/processmining/redologs/resources/r.png")));
		lblByRrzeiconsown.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblByRrzeiconsown.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblByRrzeiconsown);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0,0,0,128));
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("RedoLog Inspector");
		lblNewLabel.setForeground(Color.WHITE);
		panel.add(lblNewLabel);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Version "+Constants.VERSION);
		lblNewLabel_1.setForeground(Color.WHITE);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel(Constants.AUTHOR);
		lblNewLabel_2.setForeground(Color.WHITE);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel lblNewLabel_3 = new JLabel("<"+Constants.EMAIL+">");
		lblNewLabel_3.setForeground(Color.WHITE);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(Constants.EMAIL));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setAlignmentX(Component.CENTER_ALIGNMENT);

		setLocationRelativeTo(null);
	}

}
