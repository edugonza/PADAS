package org.processmining.redologs.ui;

import org.processmining.redologs.common.DataModel;
import org.processmining.redologs.ui.components.CustomInternalFrame;
import org.processmining.redologs.ui.components.DataModelList;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.processmining.redologs.ui.components.DataModelTree;

import javax.swing.JList;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;

import java.awt.FlowLayout;
import java.awt.Dimension;

public class FramePerspectivesInteraction extends CustomInternalFrame {

	private DataModelTree tree = new DataModelTree();
	private DataModel model = null;
	
	public FramePerspectivesInteraction() {
		super("Perspectives Interaction Miner");
		setBounds(100, 100, 560, 384);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.NORTH);
		
		JButton btnLoadDataModel = new JButton("Load Data Model");
		btnLoadDataModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataModel dm = FrameDataModels.getInstance().getSelectedDataModel();
				if (dm != null) {
					FramePerspectivesInteraction.this.model = dm;
					tree.setDataModel(dm);
				}
			}
		});
		panel_2.add(btnLoadDataModel);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane(tree);
		scrollPane_2.setPreferredSize(new Dimension(280, 200));
		panel.add(scrollPane_2, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(1, 2, 0, 0));
		
		DataModelList list = new DataModelList();
		
		JScrollPane scrollPane = new JScrollPane(list);
		panel_1.add(scrollPane);
		
		DataModelList list_1 = new DataModelList();
		
		JScrollPane scrollPane_1 = new JScrollPane(list_1);
		panel_1.add(scrollPane_1);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.SOUTH);
		
	}
}
