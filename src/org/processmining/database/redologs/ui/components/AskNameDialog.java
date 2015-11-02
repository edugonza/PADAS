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

public class AskNameDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private String name;

	/**
	 * Create the dialog.
	 */
	public AskNameDialog(JComponent comp) {
		setModal(true);
		setTitle("Name");		
		setBounds(100, 100, 382, 137);
		
		if (comp != null) {
			setLocationRelativeTo(comp);
		}
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Please provide a name for this element: ");
			contentPanel.add(lblNewLabel);
		}
		{
			txtName = new JTextField();
			contentPanel.add(txtName);
			txtName.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						name = txtName.getText();
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
