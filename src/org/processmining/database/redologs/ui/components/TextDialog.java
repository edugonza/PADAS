package org.processmining.database.redologs.ui.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Component;

public class TextDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String name;

	/**
	 * Create the dialog.
	 */
	public TextDialog(JComponent comp) {
		setModal(true);
		setTitle("Name");		
		setBounds(100, 100, 382, 137);
		
		if (comp != null) {
			setLocationRelativeTo(comp);
		}
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JLabel lblNewLabel = new JLabel("Please provide the list of files, one per line:");
			lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel);
		}
		
		final JTextArea textArea = new JTextArea();
		contentPanel.add(textArea);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						name = textArea.getText();
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						name = null;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public String showDialog() {
		setVisible(true);
		dispose();
		return name;
	}
}
