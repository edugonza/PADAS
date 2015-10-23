package org.processmining.redologs.ui.components.metamodel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.processmining.openslex.metamodel.SLEXMMSQLResult;
import org.processmining.openslex.metamodel.SLEXMMSQLResultSet;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;

public class SQLQueryPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5849304365480188323L;
	private int id = 0;
	private SLEXMMStorageMetaModel slxmm = null;
	
	private JTable sqlResultTable = null;
	private JTextArea sqlQueryField = null;
	private JButton btnExecuteSQLQuery = null;
	
	private JScrollPane scrollPane = null;
	private JProgressBar progressBar = null;
	
	public int getId() {
		return this.id;
	}
	
	public SQLQueryPanel(SLEXMMStorageMetaModel slxmm, int id) {
		super();
		
		this.id = id;
		
		this.slxmm = slxmm;
		
		this.setLayout(new BorderLayout(0, 0));

		JPanel sqlQueryPanel = new JPanel();
		this.add(sqlQueryPanel, BorderLayout.NORTH);

		sqlQueryField = new JTextArea(5,0);
		JScrollPane scrollQueryPane = new JScrollPane(sqlQueryField);

		btnExecuteSQLQuery = new JButton("Execute SQL Query");
		sqlQueryPanel.setLayout(new BorderLayout(0, 0));

		progressBar = new JProgressBar();
		
		sqlQueryPanel.add(scrollQueryPane, BorderLayout.CENTER);
		sqlQueryPanel.add(btnExecuteSQLQuery, BorderLayout.EAST);
		sqlQueryPanel.add(progressBar, BorderLayout.SOUTH);

		scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.CENTER);

		sqlResultTable = new JTable();
		sqlResultTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(sqlResultTable);
		
		btnExecuteSQLQuery.addActionListener(new ExecuteSQLQueryAction());
		
	}
	
	private void setMessage(String msg) {
		JLabel msgLabel = new JLabel();
		msgLabel.setText(msg);
		scrollPane.setViewportView(msgLabel);
	}
	
	private void setTable(JTable table) {
		scrollPane.setViewportView(table);
	}
	
	public class ExecuteSQLQueryAction implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			Thread querySQLThread = new Thread(new Runnable() {

				@Override
				public void run() {
					sqlQueryField.setEnabled(false);
					btnExecuteSQLQuery.setEnabled(false);
					progressBar.setIndeterminate(true);
					
					String query = SQLQueryPanel.this.sqlQueryField.getText();
					
					try {
						SLEXMMSQLResultSet rset = SQLQueryPanel.this.slxmm
								.executeSQL(query);

						if (rset != null) {
							SLEXMMSQLResult r = null;
							
							DefaultTableModel model = new DefaultTableModel(rset.getColumnNames(), rset.getRowCount());
							
							sqlResultTable.setModel(model);
							
							setTable(sqlResultTable);

							while ((r = rset.getNext()) != null) {
								model.addRow(r.getValues());
							}
						} else {
							System.out.println("No Values");
						}

					} catch (Exception e) {
						e.printStackTrace();
						System.err.println("Error executing sql query: "
								+ query);
						String msg = e.getMessage();
						if (msg == null) {
							msg = e.toString();
						}
						setMessage(msg);
					} finally {
						sqlQueryField.setEnabled(true);
						btnExecuteSQLQuery.setEnabled(true);
						progressBar.setIndeterminate(false);
					}
				}
			});
			
			querySQLThread.start();
		}
	}
}