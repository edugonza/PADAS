package org.processmining.redologs.ui.components.metamodel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import org.processmining.database.metamodel.poql.POQLRunner;
import org.processmining.database.metamodel.poql.QueryResult;
import org.processmining.openslex.metamodel.SLEXMMEvent;
import org.processmining.openslex.metamodel.SLEXMMObject;
import org.processmining.openslex.metamodel.SLEXMMObjectVersion;
import org.processmining.openslex.metamodel.SLEXMMStorageMetaModel;
import org.processmining.redologs.ui.components.Autocomplete;

public class POQLQueryPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5849304365480188323L;
	private int id = 0;
	private SLEXMMStorageMetaModel slxmm = null;
	
	private JTable sqlResultTable = null;
	private JTextField poqlQueryField = null;
	
	/**/
	private static final String COMMIT_ACTION = "commit";
	private static final String SHIFT_ACTION = "shift";
	
	public int getId() {
		return this.id;
	}
	
	public POQLQueryPanel(SLEXMMStorageMetaModel slxmm, int id) {
		super();
		
		this.id = id;
		
		this.slxmm = slxmm;
		
		this.setLayout(new BorderLayout(0, 0));

		JPanel sqlQueryPanel = new JPanel();
		this.add(sqlQueryPanel, BorderLayout.NORTH);

		poqlQueryField = new JTextField();
		
		// Without this, cursor always leaves text field
		poqlQueryField.setFocusTraversalKeysEnabled(false);
		Autocomplete autoComplete = new Autocomplete(poqlQueryField, new ArrayList<String>());
		poqlQueryField.getDocument().addDocumentListener(autoComplete);
		// Maps the tab key to the commit action, which finishes the
		// autocomplete
		// when given a suggestion
		poqlQueryField.getInputMap().put(KeyStroke.getKeyStroke("TAB"),SHIFT_ACTION);
		poqlQueryField.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),COMMIT_ACTION);
		poqlQueryField.getActionMap().put(COMMIT_ACTION,autoComplete.new CommitAction());
		poqlQueryField.getActionMap().put(SHIFT_ACTION,autoComplete.new ShiftAction());

		JButton btnExecutePOQLQuery = new JButton("Execute POQL Query");
		sqlQueryPanel.setLayout(new BorderLayout(0, 0));

		sqlQueryPanel.add(poqlQueryField, BorderLayout.CENTER);
		sqlQueryPanel.add(btnExecutePOQLQuery, BorderLayout.EAST);

		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.CENTER);

		sqlResultTable = new JTable();
		sqlResultTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(sqlResultTable);
		
		btnExecutePOQLQuery.addActionListener(new ExecutePOQLQueryAction());
		
	}
	
	public class ExecutePOQLQueryAction implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			Thread queryPOQLThread = new Thread(new Runnable() {

				@Override
				public void run() {
					String query = poqlQueryField.getText();
					POQLRunner runner = new POQLRunner();
					QueryResult qr = runner.executeQuery(slxmm, query);

					if (qr.type == SLEXMMObject.class) {
						//setObjectsTableContentFiltered(qr.result);
					} else if (qr.type == SLEXMMObjectVersion.class) {
						//setObjectVersionsTableContent(qr.result); // FIXME
					} else if (qr.type == SLEXMMEvent.class) {
						//setEventsTableContentFiltered(qr.result);
					} else {
						System.err.println("Unknown type of result");
					}
				}
			});
			queryPOQLThread.start();
		}
	}
}
