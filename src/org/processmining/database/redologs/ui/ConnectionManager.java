package org.processmining.database.redologs.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListDataListener;
import javax.swing.AbstractListModel;

import java.awt.GridLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.JLabel;

import org.processmining.database.redologs.config.Config;
import org.processmining.database.redologs.config.DatabaseConnectionData;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ListSelectionModel;

public class ConnectionManager extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JList<DatabaseConnectionData> listConnections;
	DefaultListModel<DatabaseConnectionData> listConnectionsModel;
	
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
				JScrollPane listScrollPane = new JScrollPane();
				listConnections = new JList<DatabaseConnectionData>();
				listScrollPane.setViewportView(listConnections);
				listConnections.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				listConnectionsModel = new DefaultListModel<>();
				listConnections.setModel(listConnectionsModel);
				for (DatabaseConnectionData e: Config.getInstance().getConnections()) {
					listConnectionsModel.addElement(e);
				}
				listConnections.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel.add(listScrollPane);
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
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DatabaseConnectionData connection = new DatabaseConnectionData();
						EditConnectionDialog editDialog = new EditConnectionDialog(connection);
						if (editDialog.showDialog()) {
							Config.getInstance().getConnections().add(connection);
							listConnectionsModel.addElement(connection);
							Config.getInstance().save();
						}
					}
				});
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
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DatabaseConnectionData connection = listConnections.getSelectedValue();
						if (connection != null) {
							EditConnectionDialog editDialog = new EditConnectionDialog(
									connection);
							if (editDialog.showDialog()) {
								listConnections.repaint();
								Config.getInstance().save();
							}
						}
					}
				});
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
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DatabaseConnectionData connection = listConnections.getSelectedValue();
						if (connection != null) {
							Config.getInstance().getConnections()
									.remove(connection);
							listConnectionsModel.removeElement(connection);
							Config.getInstance().save();
						}
					}
				});
				btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
				GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
				gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
				gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnNewButton_2.gridx = 0;
				gbc_btnNewButton_2.gridy = 2;
				panel.add(btnNewButton_2, gbc_btnNewButton_2);
			}
			{
				JButton btnNewButton_3 = new JButton("Connect");
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DatabaseConnectionData connection = listConnections.getSelectedValue();
						
					}
				});
				GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
				gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
				gbc_btnNewButton_3.gridx = 0;
				gbc_btnNewButton_3.gridy = 3;
				panel.add(btnNewButton_3, gbc_btnNewButton_3);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton("Close");
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ConnectionManager.this.setVisible(false);
						ConnectionManager.this.dispose();
					}
				});
				buttonPane.add(closeButton);
			}
		}
	}

}
