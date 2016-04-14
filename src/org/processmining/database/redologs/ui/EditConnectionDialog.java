package org.processmining.database.redologs.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;

import org.apache.commons.io.input.SwappedDataInputStream;
import org.processmining.database.redologs.config.DatabaseConnectionData;
import org.processmining.database.redologs.config.DatabaseTypes;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;

public class EditConnectionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtHostname;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JTextField txtDatabasename;
	private JTextField txtConnection;
	private JComboBox<DatabaseTypes> cbxDatabaseType;
	private JFormattedTextField formattedTextFieldPort;
	private boolean result = false;
	private JTextField txtService;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JTextArea redoLogsTextArea;
	private JCheckBox dictionaryCheckBox;
	private JCheckBox redoLogsCheckBox;
	private JLabel lblNewLabel_1;
	private JTextField dictionaryField;
	private JLabel lblNewLabel_2;
	private MaskFormatter formatter;
	private String dbnames = "";
	private JCheckBox switchRootCheckBox;

	/**
	 * Create the dialog.
	 */
	public EditConnectionDialog(final DatabaseConnectionData connection, JComponent comp) {
		setModal(true);
		setBounds(100, 100, 357, 412);
		
		if (comp != null) {
			setLocationRelativeTo(comp);
		}
		
		getContentPane().setLayout(new BorderLayout());
		
		{
			try {
				formatter = new MaskFormatter("#####");
			    // If you want the value to be committed on each keystroke instead of focus lost
				//formatter.setValueClass(Integer.class);
			    formatter.setCommitsOnValidEdit(true);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		{
			dbnames = "";
			for (String d: connection.dbname) {
				dbnames += d+",";
			}
		}
		
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			getContentPane().add(tabbedPane, BorderLayout.NORTH);
			tabbedPane.addTab("Connection", null, contentPanel, null);
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
					FormSpecs.RELATED_GAP_COLSPEC,
					FormSpecs.DEFAULT_COLSPEC,
					FormSpecs.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,}));
			{
				JLabel lblName = new JLabel("Name");
				contentPanel.add(lblName, "2, 2, right, default");
			}
			{
				txtConnection = new JTextField(connection.nameConnection);
				contentPanel.add(txtConnection, "4, 2, fill, default");
				txtConnection.setColumns(10);
			}
			{
				JLabel lblDatabaseType = new JLabel("Database Type");
				contentPanel.add(lblDatabaseType, "2, 4, right, default");
			}
			{
				cbxDatabaseType = new JComboBox<>();
				cbxDatabaseType.setModel(new DefaultComboBoxModel<>(DatabaseTypes.values()));
				cbxDatabaseType.setSelectedItem(connection.type);
				contentPanel.add(cbxDatabaseType, "4, 4, fill, default");
			}
			{
				JLabel lblHostname = new JLabel("Hostname");
				contentPanel.add(lblHostname, "2, 6, right, default");
			}
			{
				txtHostname = new JTextField(connection.hostname);
				contentPanel.add(txtHostname, "4, 6, fill, default");
				txtHostname.setColumns(10);
			}
			{
				JLabel lblPort = new JLabel("Port");
				contentPanel.add(lblPort, "2, 8, right, default");
			}
			formattedTextFieldPort = new JFormattedTextField();
			formatter.install(formattedTextFieldPort);
			
			formattedTextFieldPort.setText(connection.port.toString());
			contentPanel.add(formattedTextFieldPort, "4, 8, fill, default");
			{
				JLabel lblUsername = new JLabel("Username");
				contentPanel.add(lblUsername, "2, 10, right, default");
			}
			{
				txtUsername = new JTextField(connection.username);
				contentPanel.add(txtUsername, "4, 10, fill, default");
				txtUsername.setColumns(10);
			}
			{
				JLabel lblPassword = new JLabel("Password");
				contentPanel.add(lblPassword, "2, 12, right, default");
			}
			{
				pwdPassword = new JPasswordField(connection.password);
				contentPanel.add(pwdPassword, "4, 12, fill, default");
			}
			{
				JLabel lblDatabaseName = new JLabel("Database name");
				contentPanel.add(lblDatabaseName, "2, 14");
			}
			txtDatabasename = new JTextField(dbnames);
			contentPanel.add(txtDatabasename, "4, 14, fill, default");
			txtDatabasename.setColumns(10);
			{
				JLabel lblService = new JLabel("Service");
				contentPanel.add(lblService, "2, 16, right, default");
			}
			{
				txtService = new JTextField(connection.service);
				contentPanel.add(txtService, "4, 16, fill, default");
				txtService.setColumns(10);
			}
			{
				switchRootCheckBox = new JCheckBox("Switch to Root Container?");
				switchRootCheckBox.setSelected(connection.switchRootContainer);
				contentPanel.add(switchRootCheckBox, "4, 18, fill, default");
			}
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("RedoLogs", null, panel, null);
				
				{
					redoLogsCheckBox = new JCheckBox("Use online list of redo logs");
					redoLogsCheckBox.setSelected(connection.isRedoLogFilesListOnline);
					redoLogsCheckBox.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							if (redoLogsCheckBox.isSelected()) {
								redoLogsTextArea.setEnabled(false);
								lblNewLabel.setEnabled(false);
							} else {
								redoLogsTextArea.setEnabled(true);
								lblNewLabel.setEnabled(true);
							}
						}
					});
				}
				
				{
					lblNewLabel = new JLabel("Please provide the list of files, one per line:");
					lblNewLabel.setEnabled(!connection.isRedoLogFilesListOnline);
				}
				{
					redoLogsTextArea = new JTextArea();
					redoLogsTextArea.setRows(8);
					redoLogsTextArea.setEnabled(!connection.isRedoLogFilesListOnline);
					StringBuilder sb = new StringBuilder();
					for (String s : connection.redologFiles)
					{
					    sb.append(s);
					    sb.append("\n");
					}
					redoLogsTextArea.setText(sb.toString());
					scrollPane = new JScrollPane(redoLogsTextArea);
					scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
				}
				{
					dictionaryCheckBox = new JCheckBox("Use dictionary from online catalog");
					dictionaryCheckBox.setSelected(connection.isDictionaryOnline);
					dictionaryCheckBox.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							if (dictionaryCheckBox.isSelected()) {
								dictionaryField.setEnabled(false);
								lblNewLabel_1.setEnabled(false);
							} else {
								dictionaryField.setEnabled(true);
								lblNewLabel_1.setEnabled(true);
							}
						}
					});
				}
				{
					lblNewLabel_1 = new JLabel("Specify the path to a dictionary file:");
					lblNewLabel_1.setEnabled(!connection.isDictionaryOnline);
				}
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.add(redoLogsCheckBox);
				panel.add(lblNewLabel);
				panel.add(scrollPane);
				panel.add(dictionaryCheckBox);
				panel.add(lblNewLabel_1);
				{
					dictionaryField = new JTextField();
					dictionaryField.setAlignmentX(Component.LEFT_ALIGNMENT);
					panel.add(dictionaryField);
					dictionaryField.setColumns(10);
					dictionaryField.setText(connection.dictionaryPath);
					dictionaryField.setEnabled(!connection.isDictionaryOnline);
				}
				{
					lblNewLabel_2 = new JLabel("  ");					
					panel.add(lblNewLabel_2);
				}
			}
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						connection.nameConnection = txtConnection.getText();
						connection.type = (DatabaseTypes) cbxDatabaseType.getSelectedItem();
						connection.hostname = txtHostname.getText();
						connection.port = Integer.parseInt(formattedTextFieldPort.getText().trim());
						connection.username = txtUsername.getText();
						connection.password = String.valueOf(pwdPassword.getPassword());
						String dbnames = txtDatabasename.getText();
						connection.dbname = Arrays.asList(dbnames.split(","));
						connection.service = txtService.getText();
						String redoLogFilesStr = redoLogsTextArea.getText();
						String[] redoLogFilesArr = redoLogFilesStr.split("\\r?\\n");
						connection.redologFiles = Arrays.asList(redoLogFilesArr);
						connection.isDictionaryOnline = dictionaryCheckBox.isSelected();
						connection.dictionaryPath = dictionaryField.getText();
						connection.switchRootContainer = switchRootCheckBox.isSelected();
						result = true;
						EditConnectionDialog.this.setVisible(false);
						EditConnectionDialog.this.dispose();
					}
				});
				okButton.setActionCommand("Save");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						result = false;
						EditConnectionDialog.this.setVisible(false);
						EditConnectionDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public boolean showDialog() {
		setVisible(true);
		return result;
	}
}
