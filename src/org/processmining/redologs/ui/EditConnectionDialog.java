package org.processmining.redologs.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;

import org.processmining.redologs.config.DatabaseConnectionData;
import org.processmining.redologs.config.DatabaseTypes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

	/**
	 * Create the dialog.
	 */
	public EditConnectionDialog(final DatabaseConnectionData connection) {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 357, 303);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
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
		{
			formattedTextFieldPort = new JFormattedTextField();
			try {
				MaskFormatter formatter = new MaskFormatter("#####");
			    // If you want the value to be committed on each keystroke instead of focus lost
				//formatter.setValueClass(Integer.class);
			    formatter.setCommitsOnValidEdit(true);
				formatter.install(formattedTextFieldPort);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			formattedTextFieldPort.setText(connection.port.toString());
			contentPanel.add(formattedTextFieldPort, "4, 8, fill, default");
		}
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
		{
			txtDatabasename = new JTextField(connection.dbname);
			contentPanel.add(txtDatabasename, "4, 14, fill, default");
			txtDatabasename.setColumns(10);
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
						connection.dbname = txtDatabasename.getText();
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
