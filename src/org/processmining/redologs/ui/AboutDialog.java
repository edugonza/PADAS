package org.processmining.redologs.ui;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JDialog;

import java.awt.GridLayout;

import javax.swing.JLabel;
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

public class AboutDialog extends JDialog {

	private static AboutDialog _instance;
	
	public static AboutDialog getInstance() {
		if (_instance == null) {
			_instance = new AboutDialog();
		}
		return _instance;
	}
	/**
	 * Create the dialog.
	 */
	public AboutDialog() {
		setAlwaysOnTop(true);
		setUndecorated(true);
		setResizable(false);
		setModal(true);
		
		MouseAdapter closeOnMouseRelease = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				AboutDialog.this.setVisible(false);
				AboutDialog.this.dispose();
			}
		};
		
		addMouseListener(closeOnMouseRelease);
		setBounds(100, 100, 268, 365);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel lblByRrzeiconsown = new JLabel("");
		lblByRrzeiconsown.addMouseListener(closeOnMouseRelease);
		lblByRrzeiconsown.setToolTipText("<html>By RRZEicons (Own work) <br/>\n[CC-BY-SA-3.0]<br/>\nvia Wikimedia Commons</html>");
		lblByRrzeiconsown.setIcon(new ImageIcon(AboutDialog.class.getResource("/org/processminig/redologs/resources/200px-Icon-inspector.svg.png")));
		lblByRrzeiconsown.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblByRrzeiconsown.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblByRrzeiconsown);
		
		JLabel lblNewLabel = new JLabel("RedoLog Inspector");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Version "+Constants.VERSION);
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(Constants.AUTHOR);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("<"+Constants.EMAIL+">");
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
		getContentPane().add(lblNewLabel_3);

	}

}
