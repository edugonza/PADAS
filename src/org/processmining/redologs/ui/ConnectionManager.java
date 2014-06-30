package org.processmining.redologs.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractListModel;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class ConnectionManager extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public ConnectionManager() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		getContentPane().setLayout(borderLayout);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(5, 5));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(0, 1, 5, 5));
			{
				JList list = new JList();
				list.setModel(new AbstractListModel() {
					String[] values = new String[] {"Connection 1", "Connection 2"};
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
				list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel.add(list);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.EAST);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{81, 0};
			gbl_panel.rowHeights = new int[]{25, 15, 25, 15, 25, 0};
			gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JButton btnNewButton = new JButton("New");
				btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
				gbc_btnNewButton.gridx = 0;
				gbc_btnNewButton.gridy = 0;
				panel.add(btnNewButton, gbc_btnNewButton);
			}
			{
				JButton btnNewButton_1 = new JButton("Edit");
				btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
				GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
				gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
				gbc_btnNewButton_1.gridx = 0;
				gbc_btnNewButton_1.gridy = 1;
				panel.add(btnNewButton_1, gbc_btnNewButton_1);
			}
			{
				JButton btnNewButton_2 = new JButton("Delete");
				btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
				GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
				gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnNewButton_2.gridx = 0;
				gbc_btnNewButton_2.gridy = 2;
				panel.add(btnNewButton_2, gbc_btnNewButton_2);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
